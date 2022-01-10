package com.kakao.cafe.service;

import java.util.List;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.request.UserCreateRequestDto;
import com.kakao.cafe.dto.response.UserFindResponseDto;

public interface UserService {
	void save(UserCreateRequestDto user);

	List<UserFindResponseDto> getAllUser();

	UserFindResponseDto getUserById(int id);
}
