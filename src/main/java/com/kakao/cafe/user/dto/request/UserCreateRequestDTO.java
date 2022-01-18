package com.kakao.cafe.user.dto.request;

import com.kakao.cafe.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreateRequestDTO {
	private final String userId;
	private final String password;
	private final String name;
	private final String email;

	public User toEntity() {
		return User.builder()
			.userId(userId)
			.password(password)
			.name(name)
			.email(email)
			.build();
	}
}
