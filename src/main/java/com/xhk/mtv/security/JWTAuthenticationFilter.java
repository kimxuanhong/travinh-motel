package com.xhk.mtv.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhk.mtv.error.ApiErrorType;
import com.xhk.mtv.error.ErrorMessage;
import com.xhk.mtv.error.PasswordIncorrectException;
import com.xhk.mtv.error.UsernameIncorrectException;
import com.xhk.mtv.error.response.ApiErrorDetails;
import com.xhk.mtv.error.response.ErrorResponse;
import com.xhk.mtv.error.response.SuccessResponse;
import com.xhk.mtv.request.LoginRequest;
import com.xhk.mtv.response.LoginResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JWTProvider jwtProvider;

    private String TOKEN_PREFIX;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTProvider jwtProvider, String TOKEN_PREFIX) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.TOKEN_PREFIX = TOKEN_PREFIX;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        LoginRequest payload = (new ObjectMapper()).readValue(req.getInputStream(), LoginRequest.class);
        try {
            if (StringUtils.isEmpty(payload.getUsernameOrEmail())) {
                throw new UsernameIncorrectException(ErrorMessage.MISSING_EMAIL_ADDRESS.name());
            }

            if (StringUtils.isEmpty(payload.getPassword())) {
                throw new PasswordIncorrectException(ErrorMessage.MISSING_PASSWORD.name());
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(payload.getUsernameOrEmail(), payload.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (Exception ex) {
            if (ex.getCause() instanceof UsernameIncorrectException) {
                ErrorResponse<ApiErrorDetails> response = new ErrorResponse<>(new ApiErrorDetails(ApiErrorType.LOGIN_ERROR, ErrorMessage.MISSING_EMAIL_ADDRESS));
                response.printResponse(res);
            } else if (ex.getCause() instanceof PasswordIncorrectException) {
                ErrorResponse<ApiErrorDetails> response = new ErrorResponse<>(new ApiErrorDetails(ApiErrorType.LOGIN_ERROR, ErrorMessage.MISSING_PASSWORD));
                response.printResponse(res);
            } else {
                ErrorResponse<ApiErrorDetails> response = new ErrorResponse<>(new ApiErrorDetails(ApiErrorType.LOGIN_ERROR, ErrorMessage.LOGIN_FAILED));
                response.printResponse(res);
            }
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String token = jwtProvider.generate(userDetails.getUsername());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(TOKEN_PREFIX + " " + token);
        loginResponse.setDetails(userDetails);

        SuccessResponse<LoginResponse> response = new SuccessResponse<>(loginResponse);
        response.printResponse(res);
    }
}
