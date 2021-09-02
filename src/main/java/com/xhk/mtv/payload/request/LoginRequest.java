package com.xhk.mtv.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LoginRequest {
    @NotBlank(message = "MISSING_USERNAME")
    @JsonProperty("user_name")
    private String userName;

    @NotBlank(message = "MISSING_PASSWORD")
    private String password;
}
