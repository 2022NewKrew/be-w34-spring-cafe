package com.kakao.cafe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserInfoResponseDTO {
	private int id;
	private String userId;
	private String password;
	private String name;
	private String email;
}
