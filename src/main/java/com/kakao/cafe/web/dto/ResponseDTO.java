package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDTO {

  private String message;
  private HttpStatus result;

  public static ResponseDTO createSuccess() {
    return new ResponseDTO(
        "api response success!",
        HttpStatus.OK
    );
  }

}
