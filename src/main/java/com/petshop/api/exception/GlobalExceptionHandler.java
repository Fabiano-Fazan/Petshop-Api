package com.petshop.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<StandardError> cpfAlreadyExists(CpfAlreadyExistsException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, "Conflict", request.getRequestURI());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<StandardError> insufficientStock(InsufficientStockException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, "Bad Request", request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, "Not Found", request.getRequestURI());
    }

    @ExceptionHandler(AppointmentDateTimeAlreadyExistsException.class)
    public ResponseEntity<StandardError> appointmentConflict(RuntimeException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.CONFLICT, "Conflict", request.getRequestURI());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException e, HttpServletRequest request) {
        return buildErrorResponse(e, e.getStatus(), e.getStatus().getReasonPhrase(), request.getRequestURI());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(HttpServletRequest request) {
        return buildErrorResponse("The operation conflicts with existing data.", HttpStatus.CONFLICT, "Conflict", request.getRequestURI());
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<StandardError> concurrentUpdate(HttpServletRequest request) {
        return buildErrorResponse("The resource was changed by another operation. Please retry.", HttpStatus.CONFLICT, "Conflict", request.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Validation Error", request.getRequestURI());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardError> badCredentials(HttpServletRequest request) {
        return buildErrorResponse("Invalid email or password.", HttpStatus.UNAUTHORIZED, "Unauthorized", request.getRequestURI());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> accessDenied(HttpServletRequest request) {
        return buildErrorResponse("Access denied.", HttpStatus.FORBIDDEN, "Forbidden", request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> unreadableMessage(HttpServletRequest request) {
        return buildErrorResponse("Malformed or invalid request body.", HttpStatus.BAD_REQUEST, "Bad Request", request.getRequestURI());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> runtimeException(HttpServletRequest request) {
        String customMessage = "Internal error, please contact the administrator.";
        return buildErrorResponse(customMessage, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.putIfAbsent(error.getField(), error.getDefaultMessage()));
        StandardError body = errorBody("Request validation failed.", HttpStatus.BAD_REQUEST, "Validation Error", request.getRequestURI());
        body.setFieldErrors(errors);
        return ResponseEntity.badRequest().body(body);
    }

    private ResponseEntity<StandardError> buildErrorResponse(Exception e, HttpStatus status, String error, String path) {
        return buildErrorResponse(e.getMessage(), status, error, path);
    }

    private ResponseEntity<StandardError> buildErrorResponse(String message, HttpStatus status, String error, String path) {
        StandardError err = errorBody(message, status, error, path);
        return ResponseEntity.status(status).body(err);
    }

    private StandardError errorBody(String message, HttpStatus status, String error, String path) {
        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setError(error);
        err.setMessage(message);
        err.setPath(path);
        return err;
    }

}
