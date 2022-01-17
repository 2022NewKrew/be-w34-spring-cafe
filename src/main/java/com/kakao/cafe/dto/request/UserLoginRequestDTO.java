package com.kakao.cafe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginRequestDTO {
	private String userId;
	private String password;
}
