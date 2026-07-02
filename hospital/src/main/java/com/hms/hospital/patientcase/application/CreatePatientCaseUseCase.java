package com.hms.hospital.patientcase.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hms.common.UseCase;
import com.hms.common.constants.DocumentUploadStatus;
import com.hms.common.constants.PatientCaseStatus;
import com.hms.common.services.FileStorageService;
import com.hms.common.valueobject.PatientId;
import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.entity.PatientCaseDocument;
import com.hms.hospital.patientcase.services.PatientCaseService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CreatePatientCaseUseCase extends UseCase<CreatePatientCaseUseCase.Request, CreatePatientCaseUseCase.Response> {

	@Data
	@AllArgsConstructor(staticName = "of")
	public static class Request {
        private final String patientId;
        private final String name;
        private final Long hospitalId;
        private final MultipartFile file;
	}

	@Data
	@AllArgsConstructor(staticName = "of")
	public static class Response {
        private final boolean success;
	}

    private final PatientCaseService patientCaseService;
    private final FileStorageService fileStorageService;

	@Override
	public Response execute(Request request) {

//		try {
//			fileStorageService.saveFile(request.file);
//		}
//		catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//
//		List<PatientCaseDocument> documents = new ArrayList<>();
//		for (MultipartFile file : List.of(request.file)) {
//			try {
//				String fileName = fileStorageService.saveFile(file);
//				PatientCaseDocument document = PatientCaseDocument.builder()
//						.documentName(file.getOriginalFilename())
//						.contentType(file.getContentType())
//						.fileSizeBytes(file.getSize())
//						.s3Key(fileName)
//						.status(DocumentUploadStatus.PENDING)
//						.s3Bucket("uploads")
//						.uploadedBy("system")
//						.build();
//				documents.add(document);
//			} catch (IOException e) {
//				throw new RuntimeException("Failed to save file: " + file.getOriginalFilename(), e);
//			}
//		}
//        PatientCase patientCase = PatientCase.builder()
//            .patientId(PatientId.of(request.patientId))
//            .name(request.name)
//            .status(PatientCaseStatus.PENDING)
//            .build();
//
//		patientCaseService.savePatientCase(patientCase, request.hospitalId);
		return Response.of(true);
	}
}
