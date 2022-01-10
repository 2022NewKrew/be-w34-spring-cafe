package com.kakao.cafe.dto.request;

import com.kakao.cafe.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCreateRequestDto {
	private final String userId;
	private final String password;
	private final String name;
	private final String email;

	public User toDomain() {
		return User.builder()
			.userId(userId)
			.password(password)
			.name(name)
			.email(email)
			.build();
	}
}
