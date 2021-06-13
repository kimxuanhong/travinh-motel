package com.xhk.mtv.security;

import com.xhk.mtv.entity.Account;
import com.xhk.mtv.error.ErrorMessage;
import com.xhk.mtv.error.UsernameIncorrectException;
import com.xhk.mtv.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account user = accountRepository.findByEmail(email).orElseThrow(() -> new UsernameIncorrectException(ErrorMessage.MISSING_EMAIL_ADDRESS.name()));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new CustomUserDetails(user.getId(), user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), Collections.singletonList(authority));
    }
}
