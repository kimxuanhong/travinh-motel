package com.xhk.mtv.oauth2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GoogleOAuth2UserInfo{
    private String id;
    private String email;
    private boolean verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;

    public static OAuth2UserInfo createOAuth2UserInfo(GoogleOAuth2UserInfo userInfo) {
        OAuth2UserInfo info = new OAuth2UserInfo();
        info.setId(userInfo.getId());
        info.setEmail(userInfo.getEmail());
        info.setFullName(userInfo.getName());
        info.setFirstName(userInfo.getGiven_name());
        info.setLastName(userInfo.getFamily_name());
        info.setImageUrl(userInfo.getPicture());
        info.setProvider(AuthProvider.google);

        return info;
    }
}
