package com.xhk.mtv.config;

import com.xhk.mtv.security.CustomUserDetailsService;
import com.xhk.mtv.security.JWTAuthenticationFilter;
import com.xhk.mtv.security.JWTAuthorizationFilter;
import com.xhk.mtv.security.JWTProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${security.jwt.prefix}")
    private String TOKEN_PREFIX;

    private final JWTProvider tokenProvider;

    private final CustomUserDetailsService customUserDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityConfig(JWTProvider tokenProvider, CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class).authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/v2/api-docs", "/api/docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/images/**", "/audios/**").permitAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailsService, tokenProvider, TOKEN_PREFIX))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), tokenProvider, TOKEN_PREFIX))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
