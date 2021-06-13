package com.xhk.mtv.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
    @Value("${security.jwt.secret}")
    private String SECRET;

    @Value("${security.jwt.expire}")
    private Long EXPIRE;

    @Value("${security.jwt.prefix}")
    private String TOKEN_PREFIX;

    public String generate(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }

    public String verify(String token) {
        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }
}
