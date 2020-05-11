package com.xhk.mtv.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OAuth2VerifyOtpRequest extends OAuth2OtpRequest {
    @Size(min = 6, max = 6, message = "INVALID_LENGTH_OTP_NUMBER")
    @NotNull(message = "MISSING_OTP_NUMBER")
    @JsonProperty("otp_number")
    private String otp;
}
