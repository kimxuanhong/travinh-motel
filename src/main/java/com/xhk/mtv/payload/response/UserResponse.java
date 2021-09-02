package com.xhk.mtv.payload.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;
}
