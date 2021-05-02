package com.andrescassanaz.customeragesapp.config;

import com.andrescassanaz.customeragesapp.adapter.controller.exception.ControllerValidationException;
import com.andrescassanaz.customeragesapp.adapter.database.exception.DuplicateElementException;
import com.andrescassanaz.customeragesapp.adapter.database.exception.InconsistentDataException;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    private final HttpServletRequest httpServletRequest;
    private static final ZoneId UTC_ZONE = ZoneOffset.UTC;

    public ErrorHandler(final HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorResponse> handleDefault(Throwable ex) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, ErrorCode.INTERNAL_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(MethodArgumentNotValidException ex) {
        return buildBadRequestResponseError(
                () -> ex.getBindingResult().getFieldErrors().stream()
                        .map(err -> err.getField() + ": " + err.getDefaultMessage())
                        .collect(Collectors.toUnmodifiableList())
                        .toString(),
                ex);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(MethodArgumentTypeMismatchException ex) {
        return buildBadRequestResponseError(
                () -> "el parámetro " + ex.getName() + " debe ser de tipo " +
                        ex.getParameter().getParameterType().getSimpleName(),
                ex);
    }

    private ResponseEntity<ApiErrorResponse> buildBadRequestResponseError(Supplier<String> messageFn, Throwable ex) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex);
        final var message = messageFn.get();
        final var errorCode = ErrorCode.INVALID_REQUEST;
        final var wrappingException = new ControllerValidationException(errorCode, message, ex);
        return buildResponseError(HttpStatus.BAD_REQUEST, wrappingException, errorCode);
    }

    @ExceptionHandler({DuplicateElementException.class, InconsistentDataException.class})
    public ResponseEntity<ApiErrorResponse> handleConflict(GenericException ex) {
        log.error(HttpStatus.CONFLICT.getReasonPhrase(), ex);
        return buildResponseError(HttpStatus.CONFLICT, ex, ex.getCode());
    }

    private ResponseEntity<ApiErrorResponse> buildResponseError(HttpStatus httpStatus, Throwable ex, ErrorCode errorCode) {
        final var apiErrorResponse = ApiErrorResponse
                .builder()
                .timestamp(LocalDateTime.now(UTC_ZONE))
                .name(httpStatus.getReasonPhrase())
                .detail(ex.getMessage())
                .status(httpStatus.value())
                .code(errorCode.value())
                .resource(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    @Builder
    @NonNull
    @lombok.Value
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    private static class ApiErrorResponse {
        private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSSSS]['Z']";
        String name;
        Integer status;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
        LocalDateTime timestamp;
        Integer code;
        String resource;
        String detail;
    }
}

