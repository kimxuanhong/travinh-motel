package com.xhk.mtv.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xhk.mtv.error.response.ResponseStatus;
import com.xhk.mtv.entity.Account;
import com.xhk.mtv.oauth2.*;
import com.xhk.mtv.repository.AccountRepository;
import com.xhk.mtv.repository.OAuth2UserRepository;
import com.xhk.mtv.request.OAuth2OtpRequest;
import com.xhk.mtv.security.JWTProvider;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Service
public class OAuth2LoginServiceImpl implements OAuth2LoginService {

    //google
    @Value("${oauth2.google.page-login}")
    String GOOGLE_LINK_LOGIN;
    @Value("${oauth2.google.token}")
    String GOOGLE_LINK_GET_TOKEN;
    @Value("${oauth2.google.user_info}")
    String GOOGLE_LINK_GET_USER_INFO;
    @Value("${oauth2.google.client-id}")
    String GOOGLE_CLIENT_ID;
    @Value("${oauth2.google.client-secret}")
    String GOOGLE_CLIENT_SECRET;
    @Value("${oauth2.google.redirect-uri}")
    String GOOGLE_REDIRECT_URI;
    @Value("${oauth2.google.grant_type}")
    String GOOGLE_GRANT_TYPE;

    //facebook
    @Value("${oauth2.facebook.page-login}")
    String FACEBOOK_LINK_LOGIN;
    @Value("${oauth2.facebook.token}")
    String FACEBOOK_LINK_GET_TOKEN;
    @Value("${oauth2.facebook.user_info}")
    String FACEBOOK_LINK_GET_USER_INFO;
    @Value("${oauth2.facebook.client-id}")
    String FACEBOOK_CLIENT_ID;
    @Value("${oauth2.facebook.client-secret}")
    String FACEBOOK_CLIENT_SECRET;
    @Value("${oauth2.facebook.redirect-uri}")
    String FACEBOOK_REDIRECT_URI;

    @Value("${security.jwt.prefix}")
    private String TOKEN_PREFIX;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private OAuth2UserRepository auth2UserRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void oauth2Authorize(HttpServletResponse httpResponse, String provider) throws IOException {
        if (provider.trim().equals(AuthProvider.facebook.name())) {
            httpResponse.sendRedirect(FACEBOOK_LINK_LOGIN);
        } else if (provider.trim().equals(AuthProvider.google.name())) {
            httpResponse.sendRedirect(GOOGLE_LINK_LOGIN);
        } else {
            return;
        }
    }

    @Override
    public OAuth2UserInfo getUserInfoGoogle(final String accessToken) throws IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        GoogleOAuth2UserInfo googlePojo = mapper.readValue(response, GoogleOAuth2UserInfo.class);
        return GoogleOAuth2UserInfo.createOAuth2UserInfo(googlePojo);
    }

    @Override
    public OAuth2UserInfo getUserInfoFacebook(final String accessToken) throws IOException {
        String link = FACEBOOK_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        FacebookOAuth2UserInfo userInfo = mapper.readValue(response, FacebookOAuth2UserInfo.class);
        return FacebookOAuth2UserInfo.createOAuth2UserInfo(userInfo);
    }

    @Override
    public String getTokenFacebook(final String code) throws IOException {
        try {
            String response = Request.Post(FACEBOOK_LINK_GET_TOKEN)
                    .bodyForm(Form.form().add("client_id", FACEBOOK_CLIENT_ID)
                            .add("client_secret", FACEBOOK_CLIENT_SECRET)
                            .add("redirect_uri", FACEBOOK_REDIRECT_URI)
                            .add("code", code).build())
                    .execute().returnContent().asString();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response).get("access_token");
            return node.textValue();
        } catch (HttpResponseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getTokenGoogle(final String code) throws IOException {
        try {
            String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                    .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                            .add("client_secret", GOOGLE_CLIENT_SECRET)
                            .add("redirect_uri", GOOGLE_REDIRECT_URI)
                            .add("code", code)
                            .add("grant_type", GOOGLE_GRANT_TYPE).build())
                    .execute().returnContent().asString();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response).get("access_token");
            return node.textValue();
        } catch (HttpResponseException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void oauth2Callback(HttpServletResponse response, HttpServletRequest request, String provider) throws IOException {
        OAuth2UserInfo userInfo = null;
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            return;
        }

        if (provider.trim().equals(AuthProvider.facebook.name())) {
            String accessToken = getTokenFacebook(code);
            userInfo = getUserInfoFacebook(accessToken);
        } else if (provider.trim().equals(AuthProvider.google.name())) {
            String accessToken = getTokenGoogle(code);
            userInfo = getUserInfoGoogle(accessToken);
        }

        if (Objects.nonNull(userInfo)) {
            processOAuth2User(userInfo, response);
        } else {
            return;
        }
    }

    private void processOAuth2User(OAuth2UserInfo userInfo, HttpServletResponse response) throws IOException {
        OAuth2User user = auth2UserRepository.findByProviderAndProviderId(userInfo.getProvider(), userInfo.getId());
        if (Objects.nonNull(user)) {
            if (user.isVerifyPhoneNumber()) {
                responseAccessToken(user, response);
            } else {
                verifyPhoneNumber(user, response);
            }
        } else {
            OAuth2User newUser = registerOAuth2User(userInfo);
            verifyPhoneNumber(newUser, response);
        }
    }

    private OAuth2User registerOAuth2User(OAuth2UserInfo userInfo) {
        OAuth2User auth2User = new OAuth2User();
        auth2User.setProvider(userInfo.getProvider());
        auth2User.setProviderId(userInfo.getId());
        auth2User.setEmail(userInfo.getEmail());
        auth2User.setFirstName(userInfo.getFirstName());
        auth2User.setLastName(userInfo.getLastName());
        auth2User.setFullName(userInfo.getFullName());
        auth2User.setImageUrl(userInfo.getImageUrl());

        return auth2UserRepository.save(auth2User);
    }

    private void verifyPhoneNumber(OAuth2User user, HttpServletResponse response) throws IOException {
        String token = jwtProvider.generate(user.getProviderId());

        OAuth2OtpRequest otpRequest = new OAuth2OtpRequest();
        otpRequest.setRegisterToken(TOKEN_PREFIX + " " + token);
        otpRequest.setProvider(user.getProvider());
        JsonObject json = new JsonObject();
        Gson builder = new GsonBuilder().create();
        JsonElement jsonElement = builder.toJsonTree(otpRequest);
        json.addProperty("status", ResponseStatus.SUCCESS.toString());
        json.add("verify", jsonElement);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json.toString());
        writer.flush();
    }


    private Account registerNewUser(OAuth2User userInfo) {
        Account account = new Account();
        account.setPassword(passwordEncoder.encode(userInfo.getProviderId()));
        account.setEmail(userInfo.getEmail());
        account.setFirstName(userInfo.getFirstName());
        account.setLastName(userInfo.getLastName());

        return accountRepository.save(account);
    }

    private void responseAccessToken(OAuth2User auth2User, HttpServletResponse response) throws IOException {
        responseAccessToken(auth2User.getAccount(), response);
    }

    private void responseAccessToken(Account user, HttpServletResponse response) throws IOException {
        String token = jwtProvider.generate(user.getPhoneNumber());
        JsonObject json = new JsonObject();
        Gson builder = new GsonBuilder().create();
        JsonElement jsonElement = builder.toJsonTree("");
        json.addProperty("status", ResponseStatus.SUCCESS.toString());
        json.addProperty("access_token", TOKEN_PREFIX + " " + token);
        json.add("data", jsonElement);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.print(json.toString());
        writer.flush();
    }
}
