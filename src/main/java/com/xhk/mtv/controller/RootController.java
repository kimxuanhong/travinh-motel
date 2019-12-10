package com.xhk.mtv.controller;

import com.xhk.mtv.request.LoginRequest;
import com.xhk.mtv.response.LoginResponse;
import com.xhk.mtv.service.RootService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping
public class RootController {

    @Autowired
    private RootService rootService;

    @ApiOperation("Get blog")
    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse getBlogList(){
      return rootService.login();
   }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return null;
    }

}
