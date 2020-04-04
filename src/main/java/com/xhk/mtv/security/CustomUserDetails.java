package com.xhk.mtv.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter

public class CustomUserDetails extends User {
    private CustomUserInfo customUserInfo;

    public CustomUserDetails(Long id, String name, String phoneNumber, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.customUserInfo = new CustomUserInfo(id, name, phoneNumber, email);
    }
}
