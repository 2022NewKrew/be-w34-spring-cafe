package com.kakao.cafe.domain;

import com.kakao.cafe.exception.NoRequiredValueException;
import com.kakao.cafe.web.dto.LoginDTO;
import com.kakao.cafe.web.dto.SignUpDTO;
import java.sql.Timestamp;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class User {

  public static String DEFAULT_SUMMARY = "자기소개를 입력해주세요.";
  public static String DEFAULT_PROFILE = "https://t1.daumcdn.net/cfile/tistory/994F3E4D5FC9FCFF03";

  private int index;
  private final String email;
  private String nickName;
  private String password;
  private String summary;
  private String profile;
  private Timestamp createAt;
  private Timestamp modifiedAt;
  private Timestamp lastLoginAt;


  private User(int index, String email, String nickName, String summary, String profile,
      String password, Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt) {
    this.index = index;
    this.email = email;
    this.nickName = nickName;
    this.summary = StringUtils.isNotBlank(summary) ? summary : DEFAULT_SUMMARY;
    this.profile = StringUtils.isNotBlank(profile) ? profile : DEFAULT_PROFILE;
    this.password = password;
    this.createAt = createAt != null ? createAt : new Timestamp(System.currentTimeMillis());
    this.modifiedAt = modifiedAt != null ? modifiedAt : new Timestamp(System.currentTimeMillis());
    this.lastLoginAt =
        lastLoginAt != null ? lastLoginAt : new Timestamp(System.currentTimeMillis());
  }


  public static User of(SignUpDTO signUpDTO) {
    String email = signUpDTO.getEmail();
    String nickName = signUpDTO.getNickName();
    String password = signUpDTO.getPassword();

    if (StringUtils.isBlank(email) || StringUtils.isBlank(nickName)
        || StringUtils.isBlank(password)) {
      throw new NoRequiredValueException();
    }

    return new User(
        0, email, nickName, null,
        null, password, null, null, null);
  }


  public static User of(LoginDTO loginDTO) {
    String email = loginDTO.getEmail();
    String password = loginDTO.getPassword();

    if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
      throw new NoRequiredValueException();
    }

    return new User(0, email, "", null,
        null, password, null, null, null);
  }


  public static User create(int index, String email, String nickName,
      String summary, String profile, String password,
      Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt) {

    return new User(index, email, nickName, summary, profile,
        password, createAt, modifiedAt, lastLoginAt);
  }


  public static User createEmpty() {
    return new User(0, null, null, null,
        null, null, null, null, null);
  }


  public void setPasswordEncrypted() {
    //TODO
  }


  public void updateLastLoginAt() {
    this.lastLoginAt = new Timestamp(System.currentTimeMillis());
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(email, user.email);
  }


  @Override
  public int hashCode() {
    return Objects.hash(email);
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
