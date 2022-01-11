package com.kakao.cafe.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class UserFindResponseDTO {
	private final int id;
	private final String userId;
	private final String name;
	private final String email;
}
