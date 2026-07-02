package com.hms.common.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	@Value("${file.upload-dir}")
	private String uploadDir;

	public String saveFile(MultipartFile file) throws IOException {

		createUploadDirIfNotExists();

		String fileName = generateFileName(file);
		Path filePath = Paths.get(uploadDir, fileName);

		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}

	private void createUploadDirIfNotExists() throws IOException {
		Path path = Paths.get(uploadDir);

		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

	private String generateFileName(MultipartFile file) {
		String original = file.getOriginalFilename();
		String extension = "";

		if (original != null && original.contains(".")) {
			extension = original.substring(original.lastIndexOf("."));
		}

		return UUID.randomUUID() + extension;
	}
}
