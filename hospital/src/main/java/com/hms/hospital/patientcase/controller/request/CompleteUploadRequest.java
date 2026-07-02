package com.hms.hospital.patientcase.controller.request;

public record CompleteUploadRequest(
		String uploadId,
		String fileName,
		String contentType,
		long fileSize,
		int totalChunks
) {}