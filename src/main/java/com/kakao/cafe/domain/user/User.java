package com.kakao.cafe.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

	private int id;
	private String accId;   // accountId
	private String accPw;   // accountPassword
	private String name;
	private String email;
}
