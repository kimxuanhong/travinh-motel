package com.xhk.mtv.response;

import com.xhk.mtv.annotation.CustomResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CustomResponse
public class LoginResponse {
    private String userName;
    private String token;
    private int role;
}
