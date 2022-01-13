package com.kakao.cafe.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignUpDTO {

  private String email;
  private String nickName;
  private String password;

}
