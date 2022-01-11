package com.kakao.cafe.web.dto;

public class SignUpDTO {

  private String email;
  private String nickName;
  private String password;

  public SignUpDTO(String email, String nickName, String password) {
    this.email = email;
    this.nickName = nickName;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
