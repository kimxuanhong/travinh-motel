package com.xhk.mtv.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xhk.mtv.error.response.ApiErrorDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"error_code", "error_type", "error_message"})
public class ApiException extends RuntimeException {

    private ApiErrorDetails errorDetails;

    public ApiException(ApiErrorType errorType, ErrorMessage errorMessage) {
        this.errorDetails = new ApiErrorDetails(errorType, errorMessage);
    }

    public ApiException() {
    }
}

