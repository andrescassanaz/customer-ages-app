package com.andrescassanaz.customeragesapp.config;

public enum ErrorCode {

    INTERNAL_ERROR(100, "Error interno del servidor"),
    DUPLICATE_KEY(110, "Código duplicado"),
    INTEGRITY_VIOLATION(120, "Los datos a actualizar son inconsistentes");

    private final int value;
    private final String reasonPhrase;

    ErrorCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
