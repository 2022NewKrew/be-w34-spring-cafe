package com.kakao.cafe.domain;

import com.kakao.cafe.web.dto.SignUpDTO;
import java.sql.Timestamp;

public class User {

  public static String DEFAULT_SUMMARY = "자기소개를 입력해주세요.";
  public static String DEFAULT_PROFILE = "https://t1.daumcdn.net/cfile/tistory/994F3E4D5FC9FCFF03";

  private int index;
  private String email;
  private String nickName;
  private String password;
  private String summary;
  private String profile;
  private Timestamp createAt;
  private Timestamp modifiedAt;
  private Timestamp lastLoginAt;

  private User() {
  }

  private User(String email, String nickName, String summary, String profile, String password) {
    this.email = email;
    this.nickName = nickName;
    this.summary = summary;
    this.profile = profile;
    this.password = password;
  }

  private User(int index, String email, String nickName, String summary, String profile,
      String password, Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt) {
    this.index = index;
    this.email = email;
    this.nickName = nickName;
    this.summary = summary;
    this.profile = profile;
    this.password = password;
    this.createAt = createAt;
    this.modifiedAt = modifiedAt;
    this.lastLoginAt = lastLoginAt;
  }

  public static User of(SignUpDTO signUpDTO) {
    return new User(
        signUpDTO.getEmail(),
        signUpDTO.getNickName(),
        DEFAULT_SUMMARY,
        DEFAULT_PROFILE,
        signUpDTO.getPassword()
    );
  }

  public static User of(int index, String email, String nickName, String summary, String profile,
      String password, Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt) {
    return new User(index, email, nickName, summary, profile, password, createAt, modifiedAt,
        lastLoginAt);
  }

  public void setPasswordEncrypted() {

  }

  public String getEmail() {
    return email;
  }

  public String getNickName() {
    return nickName;
  }

  public String getPassword() {
    return password;
  }

  public Timestamp getCreateAt() {
    return createAt;
  }

  public Timestamp getLastLoginAt() {
    return lastLoginAt;
  }

  public int getIndex() {
    return index;
  }

  public Timestamp getModifiedAt() {
    return modifiedAt;
  }

  public String getSummary() {
    return summary;
  }

  public String getProfile() {
    return profile;
  }

  @Override
  public String toString() {
    return "User{" +
        "index=" + index +
        ", email='" + email + '\'' +
        ", nickName='" + nickName + '\'' +
        ", password='" + password + '\'' +
        ", summary='" + summary + '\'' +
        ", createAt=" + createAt +
        ", modifiedAt=" + modifiedAt +
        ", lastLoginAt=" + lastLoginAt +
        '}';
  }
}
