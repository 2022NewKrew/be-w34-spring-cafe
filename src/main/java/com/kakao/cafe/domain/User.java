package com.kakao.cafe.domain;

import com.kakao.cafe.exception.NoRequiredValueException;
import com.kakao.cafe.web.dto.LoginDTO;
import com.kakao.cafe.web.dto.SignUpDTO;
import com.kakao.cafe.web.dto.UserDTO;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class User {

  public static String DEFAULT_SUMMARY = "자기소개를 입력해주세요.";
  public static String DEFAULT_PROFILE = "https://t1.daumcdn.net/cfile/tistory/994F3E4D5FC9FCFF03";

  private Integer index;
  private final Long id;
  private String email;
  private String nickName;
  private String password;
  private String summary;
  private String profile;
  private Timestamp createAt;
  private Timestamp modifiedAt;
  private Timestamp lastLoginAt;

  private User(
      Integer index, Long id, String email, String nickName,
      String summary, String profile, String password,
      Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt
  ) {
    this.index = index;
    this.id = id;
    this.email = email;
    this.nickName = nickName;
    this.summary = StringUtils.isNotBlank(summary) ? summary : DEFAULT_SUMMARY;
    this.profile = StringUtils.isNotBlank(profile) ? profile : DEFAULT_PROFILE;
    this.password = password;
    this.createAt = Optional.ofNullable(createAt)
        .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
    this.modifiedAt = Optional.ofNullable(modifiedAt)
        .orElseGet(() -> new Timestamp(System.currentTimeMillis()));
    this.lastLoginAt = lastLoginAt;
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
        null, null, email, nickName, null,
        null, password, null, null, null);
  }

  public static User of(LoginDTO loginDTO) {
    String email = loginDTO.getEmail();
    String password = loginDTO.getPassword();

    if (StringUtils.isBlank(email) || StringUtils.isBlank(password)) {
      throw new NoRequiredValueException();
    }

    return new User(null, null, email, null, null,
        null, password, null, null, null);
  }

  public static User of(Long id, User user) {
    return new User(
        null, id, user.getEmail(), user.getNickName(),
        user.getSummary(), user.getProfile(), user.getPassword(),
        user.getCreateAt(), user.getModifiedAt(), user.getLastLoginAt()
    );
  }

  public static User of(UserDTO userDTO) {
    return new User(
        null, userDTO.getId(), userDTO.getEmail(), userDTO.getNickName(),
        userDTO.getSummary(), userDTO.getProfile(), null,
        userDTO.getCreateAt(), userDTO.getModifiedAt(), userDTO.getLastLoginAt()
    );
  }

  public static User create(Integer index, Long id, String email, String nickName,
      String summary, String profile, String password,
      Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt) {

    return new User(index, id, email, nickName, summary, profile,
        password, createAt, modifiedAt, lastLoginAt);
  }

  public static User createEmpty() {
    return new User(null, null, null, null, null,
        null, null, null, null, null);
  }

  public void setPasswordHashed() {
    //TODO
  }

  public void updateLastLoginAt() {
    this.lastLoginAt = new Timestamp(System.currentTimeMillis());
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
