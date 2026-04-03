package com.gdg.Build_with_AI.global.security.exception;

import com.gdg.Build_with_AI.global.exception.CustomException;
import com.gdg.Build_with_AI.global.exception.ErrorCode;

public class UserNotFoundException extends CustomException {

  public UserNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
