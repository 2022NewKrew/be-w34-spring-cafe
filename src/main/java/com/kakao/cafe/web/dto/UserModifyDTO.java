package com.kakao.cafe.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyDTO {

  private Long id;
  private String summary;
  private String nickName;
  private String password;
  private String profile;

}
