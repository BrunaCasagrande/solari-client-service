package br.com.solari.domain.exception;

public record ErrorDetail(String field, String errorMessage, Object rejectedValue) {}
