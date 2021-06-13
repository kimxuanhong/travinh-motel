package com.xhk.mtv.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhk.mtv.error.ApiErrorType;
import com.xhk.mtv.error.ErrorMessage;
import com.xhk.mtv.error.response.ApiErrorDetails;
import com.xhk.mtv.error.response.ErrorResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final String TOKEN_PREFIX;

    private final JWTProvider tokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JWTProvider tokenProvider, String TOKEN_PREFIX) {
        super(authenticationManager);
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.TOKEN_PREFIX = TOKEN_PREFIX;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt)) {
                String userInfo = tokenProvider.verify(jwt);
                ObjectMapper objectMapper = new ObjectMapper();
                CustomUserInfo customUserInfo = objectMapper.readValue(userInfo, CustomUserInfo.class);
                String email = customUserInfo.getEmail();

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            ErrorResponse<ApiErrorDetails> errorResponse = new ErrorResponse<>(new ApiErrorDetails(ApiErrorType.INVALID_REQUEST, ErrorMessage.INVALID_FORMAT_TOKEN));
            errorResponse.printResponse(response);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX + " ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
