package com.hms.common.valueobject;

public record FieldError(
        String field,
        String message
) {}
