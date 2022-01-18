package com.kakao.cafe.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class User {
	private int id;
	private String userId;
	private String password;
	private String name;
	private String email;

	public void setId(int id) {
		this.id = id;
	}

	public void updateInfo(String password, String name, String email) {
		this.password = password;
		this.name = name;
		this.email = email;
	}
}
