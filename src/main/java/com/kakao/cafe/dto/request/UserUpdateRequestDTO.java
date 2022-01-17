package com.kakao.cafe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserUpdateRequestDTO {
	private final String userId;
	private final String password;
	private final String name;
	private final String email;
}
