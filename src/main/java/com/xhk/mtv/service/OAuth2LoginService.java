package com.xhk.mtv.service;

import com.xhk.mtv.oauth2.OAuth2UserInfo;
import com.xhk.mtv.request.OAuth2OtpRequest;
import com.xhk.mtv.request.OAuth2VerifyOtpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OAuth2LoginService {
    void oauth2Authorize(HttpServletResponse httpResponse, String provider) throws IOException;

    OAuth2UserInfo getUserInfoGoogle(String accessToken) throws IOException;

    OAuth2UserInfo getUserInfoFacebook(String accessToken) throws IOException;

    String getTokenFacebook(String code) throws IOException;

    String getTokenGoogle(String code) throws IOException;

    void oauth2Callback(HttpServletResponse response, HttpServletRequest request, String provider) throws IOException;
}
