package com.xhk.mtv.controller;

import com.xhk.mtv.error.response.ResponseUtils;
import com.xhk.mtv.payload.request.LoginRequest;
import com.xhk.mtv.payload.response.LoginResponse;
import com.xhk.mtv.payload.response.RestResponse;
import com.xhk.mtv.security.JWTAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping
public class RootController {

    @Autowired
    private JWTAuthenticationService authenticationService;

    @PostMapping("/login")
    public RestResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse loginResponse = authenticationService.authenticate(request);
        return ResponseUtils.build(loginResponse);
    }
}
