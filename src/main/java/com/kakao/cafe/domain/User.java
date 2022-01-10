package com.kakao.cafe.domain;

import com.kakao.cafe.api.dto.SignUpDTO;
import java.sql.Timestamp;

public class User {

  private String email;
  private String nickName;
  private String password;
  private Timestamp createAt;
  private Timestamp lastLoginAt;

  private User() {}

  private User(String email, String nickName, String password) {
    this.email = email;
    this.nickName = nickName;
    this.password = password;
  }

  public static User of(SignUpDTO signUpDTO) {
    return new User(signUpDTO.getEmail(), signUpDTO.getNickName(), signUpDTO.getPassword());
  }

  public void setPasswordEncrypted() {

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

  public Timestamp getCreateAt() {
    return createAt;
  }

  public void setCreateAt(Timestamp createAt) {
    this.createAt = createAt;
  }

  public Timestamp getLastLoginAt() {
    return lastLoginAt;
  }

  public void setLastLoginAt(Timestamp lastLoginAt) {
    this.lastLoginAt = lastLoginAt;
  }

  @Override
  public String toString() {
    return "User{" +
        "email='" + email + '\'' +
        ", nickName='" + nickName + '\'' +
        ", password='" + password + '\'' +
        ", createAt=" + createAt +
        ", lastLoginAt=" + lastLoginAt +
        '}';
  }
}
