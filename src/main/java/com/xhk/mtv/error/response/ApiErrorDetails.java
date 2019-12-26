package com.xhk.mtv.error.response;

import com.xhk.mtv.annotation.CustomResponse;
import com.xhk.mtv.error.ApiErrorType;
import com.xhk.mtv.error.ErrorMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@Getter
@Setter
@CustomResponse
public class ApiErrorDetails {
    private ApiErrorType errorType;

    private int errorCode;

    private String errorMessage;

    public ApiErrorDetails(ApiErrorType errorType, ErrorMessage errorMessage, String... args) {
        this.errorType = errorType;
        this.errorCode = errorType.code;
        this.errorMessage = errorMessage.desc + ", " + Arrays.toString(args);
    }
}
