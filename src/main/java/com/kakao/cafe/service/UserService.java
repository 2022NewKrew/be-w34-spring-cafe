package com.kakao.cafe.service;

import java.util.List;

import com.kakao.cafe.dto.request.UserCreateRequestDTO;
import com.kakao.cafe.dto.request.UserUpdateRequestDTO;
import com.kakao.cafe.dto.response.UserFindResponseDTO;
import com.kakao.cafe.dto.response.UserInfoResponseDTO;

public interface UserService {
	void create(UserCreateRequestDTO userCreateRequestDTO);

	List<UserFindResponseDTO> getAllUser();

	UserFindResponseDTO getUserById(int id);

	UserInfoResponseDTO getUserInfoById(int id);

	void update(int id, UserUpdateRequestDTO userUpdateRequestDTO);
}
