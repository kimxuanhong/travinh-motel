package com.xhk.mtv.security;

import com.xhk.mtv.annotation.CustomResponse;
import lombok.Data;

@Data
@CustomResponse
public class CustomUserInfo {
    private Long id;

    private String name;

    private String phoneNumber;

    private String email;

    public CustomUserInfo() {
    }

    public CustomUserInfo(Long id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
