package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.User;
import java.sql.Timestamp;

public class UserDTO {

  private int index;
  private String email;
  private String nickName;
  private Timestamp createAt;
  private Timestamp lastLoginAt;

  public UserDTO() {

  }

  public UserDTO(User user) {
    this.index = user.getIndex();
    this.email = user.getEmail();
    this.nickName = user.getNickName();
    this.createAt = user.getCreateAt();
    this.lastLoginAt = user.getLastLoginAt();
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
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

}
