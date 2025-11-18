package com.sap.bits.api.LeaveScheduler.exception;

import com.sap.bits.api.LeaveScheduler.dto.response.CustomErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // --- Utility method to build error response ---
    private ResponseEntity<CustomErrorResponse> buildError(HttpStatus status, String message, WebRequest request) {
        CustomErrorResponse response = new CustomErrorResponse(
                LocalDateTime.now(),
                message,
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, status);
    }

    // ============================
    // ==== APPLICATION ERRORS ====
    // ============================

    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoResourceFound(
            org.springframework.web.servlet.resource.NoResourceFoundException ex,
            WebRequest request) {

        logger.error("Resource Not Found (NoResourceFoundException): {}", ex.getResourcePath());

        CustomErrorResponse errorResponse = new CustomErrorResponse(
                LocalDateTime.now(),
                "Resource not found: " + ex.getResourcePath(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<CustomErrorResponse> handleBadRequest(BadRequestException ex, WebRequest request) {
        logger.error("Bad Request: {}", ex.getMessage());
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleAppAuthentication(AuthenticationException ex, WebRequest request) {
        logger.error("Authentication Failure: {}", ex.getMessage());
        return buildError(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
    }

    // ============================
    // ==== AUTHENTICATION ========
    // ============================

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentials(BadCredentialsException ex, WebRequest request) {
        logger.error("Bad Credentials: {}", ex.getMessage());
        return buildError(HttpStatus.UNAUTHORIZED, "Invalid username or password", request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        logger.error("Access Denied: {}", ex.getMessage());
        return buildError(HttpStatus.FORBIDDEN, "Access denied", request);
    }

    // ===============================
    // ==== HTTP REQUEST ISSUES ======
    // ===============================

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex,
                                                                      WebRequest request) {
        logger.warn("Method Not Allowed: {}", ex.getMessage());
        return buildError(HttpStatus.METHOD_NOT_ALLOWED, "HTTP method not allowed", request);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<CustomErrorResponse> handleUnsupportedMediaType(HttpMediaTypeNotSupportedException ex,
                                                                          WebRequest request) {
        logger.warn("Unsupported Media Type: {}", ex.getMessage());
        return buildError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Content-Type not supported", request);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<CustomErrorResponse> handleMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                            WebRequest request) {
        return buildError(HttpStatus.NOT_ACCEPTABLE, "Requested media type is not acceptable", request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CustomErrorResponse> handleMissingParams(MissingServletRequestParameterException ex,
                                                                   WebRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, "Missing required parameter: " + ex.getParameterName(), request);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<CustomErrorResponse> handleMissingPathVar(MissingPathVariableException ex,
                                                                    WebRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, "Missing path variable: " + ex.getVariableName(), request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                  WebRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, "Invalid type for parameter: " + ex.getName(), request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidJson(HttpMessageNotReadableException ex,
                                                                 WebRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, "Malformed JSON request", request);
    }

    // ==============================
    // ===== BEAN VALIDATION ========
    // ==============================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex,
                                                                      WebRequest request) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildError(HttpStatus.UNPROCESSABLE_ENTITY, errors, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
                                                                         WebRequest request) {
        String errors = ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .collect(Collectors.joining(", "));
        return buildError(HttpStatus.UNPROCESSABLE_ENTITY, errors, request);
    }

    // ================================
    // -------- DATABASE & JPA --------
    // ================================

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFound(EntityNotFoundException ex,
                                                                    WebRequest request) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex,
                                                                   WebRequest request) {
        return buildError(HttpStatus.CONFLICT, "Database constraint violation", request);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<CustomErrorResponse> handleTransactionFailure(TransactionSystemException ex,
                                                                        WebRequest request) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Transaction failed", request);
    }

    // ================================
    // ===== UNKNOWN CATCH-ALL ========
    // ================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        logger.error("Unhandled Exception: ", ex);
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request);
    }
}
