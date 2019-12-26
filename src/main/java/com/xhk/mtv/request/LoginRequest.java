package com.xhk.mtv.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LoginRequest {
    @NotNull(message = "MISSING_USERNAME")
    @JsonProperty("user_name")
    private String userName;

    @NotNull(message = "MISSING_PASSWORD")
    private String password;
}
