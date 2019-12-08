package com.xhk.mtv.controller;

import com.xhk.mtv.annotation.CustomResponse;
import com.xhk.mtv.error.ApiException;
import com.xhk.mtv.response.LoginResponse;
import com.xhk.mtv.service.RootService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
@CustomResponse
public class RootController {

    @Autowired
    private RootService rootService;

    @ApiOperation("Get blog")
    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse getBlogList() throws ApiException {
      return rootService.login();
   }

}
