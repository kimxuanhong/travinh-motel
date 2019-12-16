package com.xhk.mtv.error;

import com.xhk.mtv.error.response.ApiErrorDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private ApiErrorDetails errorDetails;

    public ApiException(ApiErrorType errorType, ErrorMessage errorMessage , String... args) {
        super(errorMessage.name());
        this.errorDetails = new ApiErrorDetails(errorType, errorMessage, args);
    }
}

