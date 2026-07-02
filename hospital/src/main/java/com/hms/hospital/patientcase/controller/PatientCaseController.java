package com.hms.hospital.patientcase.controller;

import com.hms.common.CustomPage;
import com.hms.hospital.patientcase.application.CreatePatientCaseUseCase;
import com.hms.hospital.patientcase.controller.request.CompleteUploadRequest;
import com.hms.hospital.patientcase.controller.request.CreatePatientCaseRequestDTO;
import com.hms.hospital.patientcase.entity.PatientCase;
import com.hms.hospital.patientcase.entity.PatientCaseDocument;
import com.hms.hospital.patientcase.services.ChunkedUploadService;
import com.hms.hospital.patientcase.services.PatientCaseService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/patient-case")
public class PatientCaseController {

	private final PatientCaseService patientCaseService;
	private final CreatePatientCaseUseCase createPatientCaseUseCase;
	private final ChunkedUploadService chunkedUploadService;

	@GetMapping("/all")
	public ResponseEntity<CustomPage<PatientCase>> getAllPatientCases(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return ResponseEntity.ok(CustomPage.of(
				patientCaseService.getAllPatientCases(page, size)
		));
	}

	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Boolean> createPatientCase(
			@Valid @ModelAttribute("data") CreatePatientCaseRequestDTO patientCaseRequestDTO,
			@Nullable @RequestPart("file") MultipartFile file) {

		CreatePatientCaseUseCase.Response response = createPatientCaseUseCase.execute(
				CreatePatientCaseUseCase.Request.of(
						patientCaseRequestDTO.patientId(),
						patientCaseRequestDTO.name(),
						patientCaseRequestDTO.hospitalId(),
						file
				)
		);
		return ResponseEntity.ok(response.isSuccess());
	}

	@PostMapping("/{caseId}/documents/upload-chunk")
	public ResponseEntity<Void> uploadChunk(
			@PathVariable Long caseId,
			@RequestParam("file") MultipartFile chunk,
			@RequestParam("uploadId") String uploadId,
			@RequestParam("chunkIndex") int chunkIndex,
			@RequestParam("totalChunks") int totalChunks,
			@RequestParam("fileName") String fileName) {

		chunkedUploadService.saveChunk(uploadId, chunkIndex, chunk);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{caseId}/documents/complete-upload")
	public ResponseEntity<Boolean> completeUpload(
			@PathVariable Long caseId,
			@RequestBody CompleteUploadRequest request) {

		String finalFileName = chunkedUploadService.mergeChunks(
				request.uploadId(), request.totalChunks(), request.fileName());

//		PatientCaseDocument doc = new PatientCaseDocument();
//		doc.setPatientCaseId(caseId);
//		doc.setDocumentName(request.fileName());
//		doc.setS3Key(finalFileName);
//		doc.setS3Bucket("local");
//		doc.setContentType(request.contentType());
//		doc.setFileSizeBytes(request.fileSize());
//		doc.setStatus("UPLOADED");

//		PatientCaseDocument saved = documentRepository.save(doc);
		return ResponseEntity.ok(true);
	}
}
