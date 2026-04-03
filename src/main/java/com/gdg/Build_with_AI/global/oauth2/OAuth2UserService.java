package com.gdg.Build_with_AI.global.oauth2;

import com.gdg.Build_with_AI.domain.user.entity.User;
import com.gdg.Build_with_AI.domain.user.repository.UserRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

      boolean isNewUser = false;

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String socialEmail, nickname, providerId;

        Map<String, Object> customAttributes = new HashMap<>(attributes);



      if ("google".equals(provider)) {
            providerId = (String) attributes.get("sub");

            socialEmail = (String) attributes.get("email");
            nickname = (String) attributes.get("name");

            log.info("Google Provider ID: " + providerId);
            log.info("Google Email: " + socialEmail);
            log.info("Google Name: " + nickname);

        } else {
            throw new OAuth2AuthenticationException("지원되지 않는 소셜 서비스 입니다. : " + provider);
        }

        // 회원가입 또는 로그인 처리
      User user = userRepository.findByProviderId(providerId)
          .orElse(null);

      if (user == null) {
        user = User.of(
            UUID.randomUUID().toString(),
            providerId
        );
        isNewUser = true;
      }

      if(isNewUser) {
        userRepository.save(user);
      }

        customAttributes.put("userUuid", user.getUserUuid());
        customAttributes.put("email", socialEmail);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                customAttributes,
                "email"
        );
    }
}