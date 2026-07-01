package com.hms.hospital.patientcase.model;

import java.time.LocalDateTime;

import com.hms.common.constants.DocumentUploadStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "patient_case_documents")
public class PatientCaseDocumentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "document_name", length = 255, nullable = false)
	private String documentName;

	@Column(name = "s3_key", length = 1024)
	private String s3Key;

	@Column(name = "s3_bucket")
	private String s3Bucket;

	@Column(name = "content_type")
	private String contentType;

	@Column(name = "file_size_bytes")
	private Long fileSizeBytes;

	@Column(name = "status", length = 50, nullable = false)
	private DocumentUploadStatus status = DocumentUploadStatus.PENDING;

	@Column(name = "uploaded_by", length = 36)
	private String uploadedBy;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
