package com.xhk.mtv.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xhk.mtv.annotation.CurrentUser;
import com.xhk.mtv.filter.UserQueryParam;
import com.xhk.mtv.response.UserRes;
import com.xhk.mtv.security.CustomUserDetails;
import com.xhk.mtv.security.CustomUserInfo;
import com.xhk.mtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Secured("ROLE_ADMIN")
    public UserRes getUser(@PathVariable Long id){
        return rootService.getUser(id);
    }


    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public CustomUserInfo getMe(@CurrentUser Authentication currentUser){
        CustomUserDetails userDetails = (CustomUserDetails) currentUser.getPrincipal();
        return userDetails.getCustomUserInfo();
    }
}
