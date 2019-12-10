package com.xhk.mtv.security;

import com.xhk.mtv.error.ApiErrorType;
import com.xhk.mtv.error.ApiException;
import com.xhk.mtv.error.ErrorMessage;
import com.xhk.mtv.model.Account;
import com.xhk.mtv.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        Account user = accountRepository.findByEmail(email).orElseThrow(() -> new ApiException(ApiErrorType.LOGIN_ERROR, ErrorMessage.INVALID_AUTHORIZATION));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new CustomUserDetails(user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }
}
