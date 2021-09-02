package com.xhk.mtv.controller;

import com.xhk.mtv.error.response.EmptyResponse;
import com.xhk.mtv.error.response.ResponseUtils;
import com.xhk.mtv.filter.query.UserQueryParam;
import com.xhk.mtv.payload.request.CreateAccountRequest;
import com.xhk.mtv.payload.request.RestRequest;
import com.xhk.mtv.payload.response.RestResponse;
import com.xhk.mtv.payload.response.UserResponse;
import com.xhk.mtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService rootService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Page<UserResponse>> getUsers(UserQueryParam queryParam) {
        return ResponseUtils.build(rootService.getUsers(queryParam.build()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public RestResponse<UserResponse> getUser(@PathVariable Long id) {
        return ResponseUtils.build(rootService.getUser(id));
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public EmptyResponse createUser(@Valid @RequestBody RestRequest<CreateAccountRequest> request) {
        boolean isSuccess = rootService.createUser(request);
        return EmptyResponse.instance;
    }

}
