package com.xhk.mtv.security;

import com.xhk.mtv.entity.Account;
import com.xhk.mtv.error.UsernameNotFoundException;
import com.xhk.mtv.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.lang.String.format;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account user = accountRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(format("User: %s, not found", username))
        );

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new CustomUserDetails(
                user.getId(),
                user.getFullName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
