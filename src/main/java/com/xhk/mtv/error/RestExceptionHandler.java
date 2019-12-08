package com.xhk.mtv.error;

import com.xhk.mtv.error.response.ApiErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiException errorResponse = new ApiException(ApiErrorType.INVALID_FORMAT, ErrorMessage.INVALID_REQUEST_URL);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiException errorResponse = new ApiException(ApiErrorType.INVALID_REQUEST, ErrorMessage.INVALID_REQUEST_URL);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiException errorResponse = new ApiException(ApiErrorType.INVALID_REQUEST, ErrorMessage.INVALID_REQUEST_URL);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        return ResponseEntity.badRequest().body(ex.getErrorDetails());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ApiErrorDetails apiErrorDetails = new ApiErrorDetails(ApiErrorType.SERVER_ERROR, ErrorMessage.INTERNAl_ERROR);
        return ResponseEntity.badRequest().body(apiErrorDetails);
    }
}
