package com.xhk.mtv.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xhk.mtv.error.ApiErrorType;
import com.xhk.mtv.error.ApiException;
import com.xhk.mtv.error.ErrorMessage;
import com.xhk.mtv.error.response.ErrorResponse;
import com.xhk.mtv.error.response.ResponseStatus;
import com.xhk.mtv.model.Account;
import com.xhk.mtv.repository.AccountRepository;
import com.xhk.mtv.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JWTProvider jwtProvider;

    private AccountRepository accountRepository;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AccountRepository accountRepository, JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.accountRepository = accountRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            LoginRequest payload = (new ObjectMapper()).readValue(req.getInputStream(), LoginRequest.class);

            if (StringUtils.isEmpty(payload.getUsernameOrEmail().trim())) {
                throw new ApiException(ApiErrorType.LOGIN_ERROR, ErrorMessage.MISSING_PHONE_NUMBER);
            }

            if (StringUtils.isEmpty(payload.getPassword().trim())) {
                throw new ApiException(ApiErrorType.LOGIN_ERROR, ErrorMessage.MISSING_PASSWORD);
            }

            Optional<Account> account = accountRepository.findByEmail(payload.getUsernameOrEmail());

            Authentication authentication = new UsernamePasswordAuthenticationToken(payload.getUsernameOrEmail(), payload.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new ApiException(ApiErrorType.LOGIN_ERROR, ErrorMessage.INVALID_AUTHORIZATION);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String token = jwtProvider.generate(userDetails.getUsername());
        JsonObject json = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        gsonBuilder.serializeNulls();

        json.addProperty("status", ResponseStatus.SUCCESS.toString());
        json.addProperty("access_token", "Bearer " + token);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.print(json.toString());
        writer.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException {
        //ApiException exception = (ApiException) failed;
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new ErrorResponse<>(failed));

        res.setContentType("application/json;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.print(json);
        writer.flush();
    }
}
