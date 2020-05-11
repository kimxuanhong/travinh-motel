package com.xhk.mtv.repository;

import com.xhk.mtv.oauth2.AuthProvider;
import com.xhk.mtv.oauth2.OAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuth2UserRepository extends JpaRepository<OAuth2User, Long> {
    OAuth2User findByProviderAndProviderId(AuthProvider provider, String providerId);
}
