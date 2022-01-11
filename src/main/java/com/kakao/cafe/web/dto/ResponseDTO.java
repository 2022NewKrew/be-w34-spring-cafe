package com.kakao.cafe.web.dto;

import org.springframework.http.HttpStatus;

public class ResponseDTO {

  private String message;
  private HttpStatus result;

  private ResponseDTO(String message, HttpStatus result) {
    this.message = message;
    this.result = result;
  }

  public static ResponseDTO createSuccess() {
    return new ResponseDTO(
        "api response success!",
        HttpStatus.OK
    );
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getResult() {
    return result;
  }

  public void setResult(HttpStatus result) {
    this.result = result;
  }
}
