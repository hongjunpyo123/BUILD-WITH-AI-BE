package com.gdg.Build_with_AI.global.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdg.Build_with_AI.global.filter.UserFilter;
import com.gdg.Build_with_AI.global.oauth2.CustomOAuth2AuthorizationRequestResolver;
import com.gdg.Build_with_AI.global.oauth2.OAuth2LoginSuccessHandler;
import com.gdg.Build_with_AI.global.oauth2.OAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final ObjectMapper objectMapper;
  private final OAuth2UserService OAuth2UserService;
  private final CustomOAuth2AuthorizationRequestResolver customOAuth2AuthorizationRequestResolver;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
      OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler,
      UserFilter userFilter) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/**")
            .permitAll()
        )
        .oauth2Login(oauth2 -> oauth2
            .authorizationEndpoint(authorization -> authorization
                .baseUri("/oauth2/authorization")
                .authorizationRequestResolver(customOAuth2AuthorizationRequestResolver)
            )
            .redirectionEndpoint(redirection -> redirection
                .baseUri("/login/oauth2/code/*"))
            .userInfoEndpoint(userInfo -> userInfo
                .userService(OAuth2UserService))
            .successHandler(oAuth2LoginSuccessHandler)
        )
        .addFilterBefore(userFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
  }

}
