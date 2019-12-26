package com.xhk.mtv.controller;

import com.xhk.mtv.request.LoginRequest;
import com.xhk.mtv.response.LoginResponse;
import com.xhk.mtv.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping
public class RootController {

    @Autowired
    private RootService rootService;

    @PostMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_USER")
    public LoginResponse getHome(@Valid @RequestBody LoginRequest request){
      return rootService.getHome();
   }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return null;
    }

}
