package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.User;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

  private Integer index;
  private Long id;
  private String email;
  private String nickName;
  private String summary;
  private String profile;
  private Timestamp createAt;
  private Timestamp modifiedAt;
  private Timestamp lastLoginAt;

  public UserDTO(User user) {
    this.index = user.getIndex();
    this.id = user.getId();
    this.email = user.getEmail();
    this.nickName = user.getNickName();
    this.summary = user.getSummary();
    this.profile = user.getProfile();
    this.createAt = user.getCreateAt();
    this.modifiedAt = user.getModifiedAt();
    this.lastLoginAt = user.getLastLoginAt();
  }

  public UserDTO(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
        "index=" + index +
        ", id=" + id +
        ", email='" + email + '\'' +
        ", nickName='" + nickName + '\'' +
        ", summary='" + summary + '\'' +
        ", profile='" + profile + '\'' +
        ", createAt=" + createAt +
        ", modifiedAt=" + modifiedAt +
        ", lastLoginAt=" + lastLoginAt +
        '}';
  }

}
