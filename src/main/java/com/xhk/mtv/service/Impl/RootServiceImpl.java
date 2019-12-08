package com.xhk.mtv.service.Impl;

import com.xhk.mtv.response.LoginResponse;
import com.xhk.mtv.service.RootService;
import org.springframework.stereotype.Service;

@Service
public class RootServiceImpl implements RootService {

    @Override
    public LoginResponse login() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setRole(0);
        loginResponse.setToken("kjsdhgkvhjzbvxdcjuyhbvfkjhfb");
        return loginResponse;
    }
}
