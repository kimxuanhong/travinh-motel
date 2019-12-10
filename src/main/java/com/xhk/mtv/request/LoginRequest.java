package com.xhk.mtv.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginRequest {
    @JsonProperty("username_or_email")
    private String usernameOrEmail;
    private String password;
}
