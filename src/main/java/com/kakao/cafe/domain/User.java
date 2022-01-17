package com.kakao.cafe.domain;

import com.kakao.cafe.exception.NoRequiredValueException;
import com.kakao.cafe.exception.WrongPasswordException;
import com.kakao.cafe.web.dto.SignUpDTO;
import com.kakao.cafe.web.dto.UserDTO;
import com.kakao.cafe.web.dto.UserModifyDTO;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

@Getter
public class User {

  public static String DEFAULT_SUMMARY = "자기소개를 입력해주세요.";
  public static String DEFAULT_PROFILE = "https://t1.daumcdn.net/cfile/tistory/994F3E4D5FC9FCFF03";

  private Integer index;
  private final Long id;
  private Email email;
  private String nickName;
  private String password;
  private String summary;
  private String profile;
  private Timestamp createAt;
  private Timestamp modifiedAt;
  private Timestamp lastLoginAt;
  private boolean isPasswordHashed;

  private User(
      Integer index, Long id, Email email, String nickName,
      String summary, String profile, String password,
      Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt,
      boolean isPasswordHashed
  ) {
    this.index = index;
    this.id = id;
    this.email = email;
    this.nickName = nickName;
    this.summary = summary;
    this.profile = profile;
    this.password = password;
    this.createAt = createAt;
    this.modifiedAt = modifiedAt;
    this.lastLoginAt = lastLoginAt;
    this.isPasswordHashed = isPasswordHashed;
  }


  /**
   * 최초 가입 시 기본 유저 정보 생성
   *
   * @param signUpDTO 회원가입 요청
   * @return User
   */
  public static User create(SignUpDTO signUpDTO) {
    String email = signUpDTO.getEmail();
    String nickName = signUpDTO.getNickName();
    String password = signUpDTO.getPassword();

    if (StringUtils.isBlank(email)
        || StringUtils.isBlank(nickName)
        || StringUtils.isBlank(password)
    ) {
      throw new NoRequiredValueException();
    }

    return new User(
        null,
        null,
        Email.of(email),
        nickName,
        DEFAULT_SUMMARY,
        DEFAULT_PROFILE,
        password,
        null,
        null,
        null,
        false
    );
  }


  /**
   * Repository Save 후 auto_increment ID 객체를 추가하기 위함.
   *
   * @param id   user id
   * @param user saved user
   * @return User
   */
  public static User create(Long id, User user) {
    return new User(
        null,
        id,
        user.getEmail(),
        user.getNickName(),
        user.getSummary(),
        user.getProfile(),
        user.getPassword(),
        user.getCreateAt(),
        user.getModifiedAt(),
        user.getLastLoginAt(),
        true
    );
  }


  /**
   * UserDTO 객체를 User 정보로 변환.
   *
   * @param userDTO Request/Response DTO
   * @return User
   */
  public static User create(UserDTO userDTO) {
    return new User(
        null,
        userDTO.getId(),
        userDTO.getEmail(),
        userDTO.getNickName(),
        userDTO.getSummary(),
        userDTO.getProfile(),
        null,
        userDTO.getCreateAt(),
        userDTO.getModifiedAt(),
        userDTO.getLastLoginAt(),
        false
    );
  }


  /**
   * 변경전 User 에서 변경 정보를 통해 새 User 생성
   *
   * @param user       변경전 User
   * @param modifyInfo 변경 정보
   * @return User
   */
  public static User create(User user, UserModifyDTO modifyInfo) {
    String nickName = modifyInfo.getNickName();
    String summary = modifyInfo.getSummary();
    String profile = modifyInfo.getProfile();

    return new User(
        null,
        modifyInfo.getId(),
        user.getEmail(),
        StringUtils.isNotBlank(nickName) ? nickName : "유저" + modifyInfo.getId(),
        StringUtils.isNotBlank(summary) ? summary : DEFAULT_SUMMARY,
        StringUtils.isNotBlank(profile) ? profile : DEFAULT_PROFILE,
        user.getPassword(),
        user.getCreateAt(),
        user.getModifiedAt(),
        user.getLastLoginAt(),
        user.isPasswordHashed()
    );
  }


  /**
   * DB Select 후 RowMapper 에 의해 User 생성
   *
   * @return User
   */
  public static User create(
      Integer index, Long id, String email, String nickName,
      String summary, String profile, String password,
      Timestamp createAt, Timestamp modifiedAt, Timestamp lastLoginAt
  ) {
    return new User(
        index,
        id,
        Email.of(email),
        nickName,
        summary,
        profile,
        password,
        createAt,
        modifiedAt,
        lastLoginAt,
        true
    );
  }


  /**
   * 빈 객체 생성
   *
   * @return User
   */
  public static User createEmpty() {
    return new User(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        false
    );
  }


  /**
   * 현재 유저 패스워드 해시화, BCrypt library 사용
   */
  public void setPasswordHashed() {
    if (!isPasswordHashed) {
      String salt = BCrypt.gensalt();
      password = BCrypt.hashpw(password, salt);
      isPasswordHashed = true;
    }
  }


  /**
   * 유저 패스워드 검증 현재 유저와 동일한 패스워드인지 패스워드를 검증한다.
   *
   * @param plainPassword 검증할 패스워드
   * @throws WrongPasswordException 잘못된 패스워드
   */
  public void checkPassword(String plainPassword) {
    setPasswordHashed();
    if (!BCrypt.checkpw(plainPassword, password)) {
      throw new WrongPasswordException();
    }
  }


  /**
   * 마지막 로그인 시간을 현재 시간으로 갱신
   */
  public void updateLastLoginAt() {
    this.lastLoginAt = new Timestamp(System.currentTimeMillis());
  }


  /**
   * PK 값인 id 로 equals 판단
   *
   * @param o
   * @return 동일 여부
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }


  /**
   * auto-generated by intellij
   *
   * @return hash
   */
  @Override
  public int hashCode() {
    return Objects.hash(email);
  }


  /**
   * auto-generated by intellij
   *
   * @return string for log
   */
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
