package com.xhk.mtv.response;

import com.xhk.mtv.annotation.CustomResponse;
import lombok.Data;

@Data
@CustomResponse
public class UserRes {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;
}
