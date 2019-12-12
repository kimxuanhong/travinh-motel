package com.xhk.mtv.response;

import com.xhk.mtv.annotation.CustomResponse;
import com.xhk.mtv.security.CustomUserDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CustomResponse
public class LoginResponse {
    private String token;
    private CustomUserDetails details;
}
