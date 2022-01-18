package com.kakao.cafe.user.dto.response;

import com.kakao.cafe.user.entity.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserFindResponseDTO {
	private final int id;
	private final String userId;
	private final String name;
	private final String email;

	public UserFindResponseDTO(User user) {
		this.id = user.getId();
		this.userId = user.getUserId();
		this.name = user.getName();
		this.email = user.getEmail();
	}


}
