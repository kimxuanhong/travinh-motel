package com.xhk.mtv.error;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xhk.mtv.error.response.ApiErrorDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
@JsonPropertyOrder({"error_code", "error_type", "error_message"})
public class ApiException extends AuthenticationException {

    private ApiErrorDetails errorDetails;

    public ApiException(ApiErrorType errorType, ErrorMessage errorMessage) {
        super(errorMessage.desc);
        this.errorDetails = new ApiErrorDetails(errorType, errorMessage);
    }
}

