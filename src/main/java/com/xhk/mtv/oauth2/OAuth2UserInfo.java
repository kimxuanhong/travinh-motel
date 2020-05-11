package com.xhk.mtv.oauth2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2UserInfo {
    private String id;
    private String email;
    private AuthProvider provider;
    private String fullName;
    private String firstName;
    private String lastName;
    private String imageUrl;
}
