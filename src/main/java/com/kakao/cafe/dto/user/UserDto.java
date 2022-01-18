package com.kakao.cafe.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private Integer id;
	private String accId;
	private String accPw;
	private String name;
	private String email;
	private String prevAccPw;
	private String newAccPw;
}
