package com.xhk.mtv.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.annotations.SerializedName;
import com.xhk.mtv.oauth2.AuthProvider;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OAuth2OtpRequest {
    @NotNull(message = "MISSING_REGISTER_TOKEN")
    @JsonProperty("register_token")
    @SerializedName("register_token")
    private String registerToken;
    @NotNull(message = "MISSING_PROVIDER")
    private AuthProvider provider;
    @NotNull(message = "MISSING_PHONE_NUMBER")
    private String phoneNumber;
}
