package com.xhk.mtv.security;

import com.xhk.mtv.payload.request.LoginRequest;
import com.xhk.mtv.payload.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static com.xhk.mtv.common.Constants.TOKEN_PREFIX;

@Component
public class JWTAuthenticationService {

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse authenticate(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        String token = jwtProvider.generate(userDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(String.format("%s%s", TOKEN_PREFIX, token));
        loginResponse.setDetails(userDetails);

        return loginResponse;
    }
}
