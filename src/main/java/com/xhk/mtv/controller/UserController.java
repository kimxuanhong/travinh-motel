package com.xhk.mtv.controller;

import com.xhk.mtv.filter.UserQueryParam;
import com.xhk.mtv.response.UserRes;
import com.xhk.mtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService rootService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Page<UserRes> getUsers(UserQueryParam queryParam){
      return rootService.getUsers(queryParam.build(null));
   }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRes getUser(@PathVariable Long id){
        return rootService.getUser(id);
    }

}
