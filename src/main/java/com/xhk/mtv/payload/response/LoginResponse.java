package com.xhk.mtv.payload.response;

import com.xhk.mtv.security.CustomUserDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private CustomUserDetails details;
}
