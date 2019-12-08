package com.xhk.mtv.error.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.xhk.mtv.annotation.CustomResponse;
import com.xhk.mtv.error.ApiErrorType;
import com.xhk.mtv.error.ErrorMessage;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel
@CustomResponse
@JsonPropertyOrder({"error_type", "error_code", "error_message"})
public class ApiErrorDetails {
    @JsonProperty("error_type")
    private ApiErrorType errorType;

    @JsonProperty("error_code")
    private int errorCode;

    @JsonProperty("error_message")
    private String errorMessage;

    public ApiErrorDetails(ApiErrorType errorType, ErrorMessage errorMessage) {
        this.errorType = errorType;
        this.errorCode = errorMessage.code;
        this.errorMessage = errorMessage.desc;
    }

    public ApiErrorDetails() {
    }
}
