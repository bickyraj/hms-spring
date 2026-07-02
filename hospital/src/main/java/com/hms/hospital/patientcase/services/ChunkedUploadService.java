package com.hms.hospital.patientcase.services;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChunkedUploadService {

	private final Path uploadPath;
	private final Path tempChunkPath;

	public ChunkedUploadService(@Value("${file.upload-dir}") String uploadDir) throws IOException {
		this.uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
		this.tempChunkPath = uploadPath.resolve("temp-chunks");
		Files.createDirectories(uploadPath);
		Files.createDirectories(tempChunkPath);
	}

	public void saveChunk(String uploadId, int chunkIndex, MultipartFile chunk) {
		try {
			Path uploadDir = tempChunkPath.resolve(uploadId);
			Files.createDirectories(uploadDir);

			Path chunkFile = uploadDir.resolve("chunk_" + chunkIndex);
			Files.copy(chunk.getInputStream(), chunkFile, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException("Failed to save chunk " + chunkIndex + " for upload " + uploadId, e);
		}
	}

	public String mergeChunks(String uploadId, int totalChunks, String originalFileName) {
		try {
			Path uploadDir = tempChunkPath.resolve(uploadId);
			String finalFileName = UUID.randomUUID() + "-" + StringUtils.cleanPath(originalFileName);
			Path finalFile = uploadPath.resolve(finalFileName);

			try (OutputStream out = Files.newOutputStream(finalFile, StandardOpenOption.CREATE)) {
				for (int i = 0; i < totalChunks; i++) {
					Path chunkFile = uploadDir.resolve("chunk_" + i);
					if (!Files.exists(chunkFile)) {
						throw new RuntimeException("Missing chunk " + i + " for upload " + uploadId);
					}
					Files.copy(chunkFile, out);
				}
			}

			// cleanup temp chunks
			FileSystemUtils.deleteRecursively(uploadDir);

			return finalFileName;
		} catch (IOException e) {
			throw new RuntimeException("Failed to merge chunks for upload " + uploadId, e);
		}
	}
}
