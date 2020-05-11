package com.xhk.mtv.controller;

import com.xhk.mtv.request.LoginRequest;
import com.xhk.mtv.response.LoginResponse;
import com.xhk.mtv.service.OAuth2LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping
public class RootController {
    @Autowired
    private OAuth2LoginService auth2LoginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return null;
    }

    @GetMapping("oauth2/authorize/{provider}")
    public void oauth2Authorize(@PathVariable String provider, HttpServletResponse response) throws IOException {
        auth2LoginService.oauth2Authorize(response, provider);
    }

    @GetMapping("/oauth2/callback/{provider}")
    public void oauth2Callback(@PathVariable String provider, HttpServletResponse response, HttpServletRequest request) throws IOException {
        auth2LoginService.oauth2Callback(response, request, provider);
    }
}
