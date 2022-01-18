package com.kakao.cafe.common;

import com.kakao.cafe.user.entity.User;

import lombok.Getter;

@Getter
public class SessionUser {
	private final int id;
	private final String userId;
	private final String password;
	private final String name;
	private final String email;

	public SessionUser(User user) {
		this.id = user.getId();
		this.userId = user.getUserId();
		this.password = user.getPassword();
		this.name = user.getName();
		this.email = user.getEmail();
	}
}
