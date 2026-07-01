package com.hms.hospital.patientcase.entity;

import java.time.LocalDateTime;

import com.hms.common.constants.DocumentUploadStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PatientCaseDocument {

	private Long id;
	private String documentName;
	private String s3Key;
	private String s3Bucket;
	private String contentType;
	private Long fileSizeBytes;
	private DocumentUploadStatus status;
	private String uploadedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
