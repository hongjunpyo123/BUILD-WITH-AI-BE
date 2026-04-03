package com.gdg.Build_with_AI.domain.user.controller;

import com.gdg.Build_with_AI.domain.test.dto.TestRequest;
import com.gdg.Build_with_AI.global.common.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

  @GetMapping("/get-test")
  public ResponseDTO<?> getTest() {
    return ResponseDTO.of("테스트에 성공하셨습니다.");
  }

  @PostMapping("/post-test")
  public ResponseDTO<?> postTest(@RequestBody TestRequest test) {
    return ResponseDTO.of("테스트에 성공하셨습니다." + test.getTest());
  }

}
