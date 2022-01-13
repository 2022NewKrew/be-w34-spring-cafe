package com.kakao.cafe.web.dto;

import com.kakao.cafe.exception.ErrorConst;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO extends ResponseDTO {

  private String code;

  private ErrorDTO(ErrorConst error) {
    super(error.getMessage(), error.getStatus());
    this.code = error.getCode();
  }

  public static ErrorDTO of(ErrorConst errorConst) {
    return new ErrorDTO(errorConst);
  }

}
