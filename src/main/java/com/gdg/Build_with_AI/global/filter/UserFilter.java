package com.gdg.Build_with_AI.global.filter;

import com.gdg.Build_with_AI.domain.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFilter extends OncePerRequestFilter {

  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String url = request.getRequestURI();
    String method = request.getMethod();

    ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 10000);
    ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

    filterChain.doFilter(wrappedRequest, response);

    byte[] body = wrappedRequest.getContentAsByteArray();
    if (body.length > 0) {
      String bodyStr = new String(body, wrappedRequest.getCharacterEncoding());
      log.info("[{}] {}\n{}", method, url, bodyStr);
    } else {
      log.info("[{}] {}", method, url);
    }
  }

}
