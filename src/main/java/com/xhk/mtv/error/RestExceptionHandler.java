package com.xhk.mtv.error;

import com.xhk.mtv.error.response.ApiErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        ApiException errorResponse = new ApiException(ApiErrorType.INVALID_FORMAT, ErrorMessage.INVALID_REQUEST_URL);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ex.printStackTrace();
        ApiException errorResponse = new ApiException(ApiErrorType.INVALID_REQUEST, ErrorMessage.INVALID_REQUEST_URL);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ex.printStackTrace();
        ApiException errorResponse = new ApiException(ApiErrorType.INVALID_REQUEST, ErrorMessage.INVALID_REQUEST_URL);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        ex.printStackTrace();
        return ResponseEntity.badRequest().body(ex.getErrorDetails());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ex.printStackTrace();
        ApiErrorDetails apiErrorDetails = new ApiErrorDetails(ApiErrorType.SERVER_ERROR, ErrorMessage.INTERNAl_ERROR);
        return ResponseEntity.badRequest().body(apiErrorDetails);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        ex.printStackTrace();
        ApiErrorDetails apiErrorDetails = new ApiErrorDetails(ApiErrorType.INVALID_AUTHORITY, ErrorMessage.INVALID_AUTHORIZATION);
        return ResponseEntity.badRequest().body(apiErrorDetails);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<Object> handleVersionEntityException(ObjectOptimisticLockingFailureException ex) {
        ex.printStackTrace();
        ApiErrorDetails apiErrorDetails = new ApiErrorDetails(ApiErrorType.INVALID_REQUEST, ErrorMessage.OLD_VERSION_OBJECT);
        return ResponseEntity.badRequest().body(apiErrorDetails);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });
        ApiErrorDetails apiErrorDetails = new ApiErrorDetails(ApiErrorType.INVALID_REQUEST, ErrorMessage.LOGIN_FAILED, errors.get(0));
        return ResponseEntity.badRequest().body(apiErrorDetails);
    }

}
