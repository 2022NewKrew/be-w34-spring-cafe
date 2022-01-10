package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
	private int id;
	private String userId;
	private String password;
	private String name;
	private String email;
}
