package com.gdg.Build_with_AI.global.security.core;

import com.gdg.Build_with_AI.domain.user.entity.User;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

  private final User user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections
        .singleton(
            new SimpleGrantedAuthority("ROLE_USER")
        );
  }

  @Override
  public @Nullable String getPassword() {
    return ""; //소셜 로그인 사용중이므로 빈 문자열로 설정합니다.
  }

  public String getUuid() {
    return user.getUserUuid();
  }

  @Override
  public String getUsername() {
    return user.getUserUuid();
  }
}

