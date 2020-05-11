package com.xhk.mtv.oauth2;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FacebookOAuth2UserInfo {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String name;
    Picture picture;

    public static OAuth2UserInfo createOAuth2UserInfo(FacebookOAuth2UserInfo userInfo) {
        OAuth2UserInfo info = new OAuth2UserInfo();
        info.setId(userInfo.getId());
        info.setEmail(userInfo.getEmail());
        info.setFullName(userInfo.getName());
        info.setFirstName(userInfo.getFirst_name());
        info.setLastName(userInfo.getLast_name());
        info.setImageUrl(userInfo.getPicture().getData().getUrl());
        info.setProvider(AuthProvider.facebook);

        return info;
    }
}

@Setter
@Getter
class Picture {
    Data data;
}

@Setter
@Getter
class Data {
    private float height;
    private boolean is_silhouette;
    private String url;
    private float width;

    public void setIs_silhouette(boolean is_silhouette) {
        this.is_silhouette = is_silhouette;
    }
}