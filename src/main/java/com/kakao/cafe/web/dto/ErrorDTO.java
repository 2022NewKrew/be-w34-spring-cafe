package com.kakao.cafe.web.dto;

import com.kakao.cafe.exception.ErrorConst;

public class ErrorDTO extends ResponseDTO {

  private String code;

  private ErrorDTO(ErrorConst error) {
    super(error.getMessage(), error.getStatus());
    this.code = error.getCode();
  }

  public static ErrorDTO of(ErrorConst errorConst) {
    return new ErrorDTO(errorConst);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
