package com.xhk.mtv.error;

import com.xhk.mtv.error.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xhk.mtv.common.Constants.EMPTY_STRING;
import static com.xhk.mtv.error.Status.*;
import static java.util.Optional.ofNullable;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleException(BusinessException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(BUSINESS_LOGIC_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleException(UsernameNotFoundException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(INVALID_AUTHORIZATION, ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleException(BadCredentialsException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(INVALID_AUTHORIZATION, ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleException(NotFoundException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(RESOURCE_NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handleException(InternalAuthenticationServiceException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(INVALID_AUTHORIZATION, ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleException(ValidationException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(VALIDATION_EXCEPTION, ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleException(MissingServletRequestParameterException ex) {

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(MISSING_REQUEST_PARAMETER, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException ex) {

        Map<String, String> details = new HashMap<>();
        details.put("paramName", ex.getName());
        details.put("paramValue", ofNullable(ex.getValue()).map(Object::toString).orElse(EMPTY_STRING));
        details.put("errorMessage", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(METHOD_ARGUMENT_TYPE_MISMATCH, details));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException ex) {

        List<Map<String, String>> details = new ArrayList<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    Map<String, String> detail = new HashMap<>();
                    detail.put("objectName", fieldError.getObjectName());
                    detail.put("field", fieldError.getField());
                    detail.put("rejectedValue", "" + fieldError.getRejectedValue());
                    detail.put("errorMessage", fieldError.getDefaultMessage());
                    details.add(detail);
                });

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.build(METHOD_ARGUMENT_VALIDATION_FAILED, details));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleException(AccessDeniedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.build(FORBIDDEN, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.build(INTERNAL_SERVER_ERROR, ex.getMessage()));
    }
}
