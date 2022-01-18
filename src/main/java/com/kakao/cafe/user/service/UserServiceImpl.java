package com.kakao.cafe.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kakao.cafe.common.SessionUser;
import com.kakao.cafe.common.exception.IllegalLoginException;
import com.kakao.cafe.user.dto.request.UserCreateRequestDTO;
import com.kakao.cafe.user.dto.request.UserLoginRequestDTO;
import com.kakao.cafe.user.dto.request.UserUpdateRequestDTO;
import com.kakao.cafe.user.dto.response.UserFindResponseDTO;
import com.kakao.cafe.user.dto.response.UserInfoResponseDTO;
import com.kakao.cafe.user.entity.User;
import com.kakao.cafe.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public void create(UserCreateRequestDTO userCreateRequestDto) {
		userRepository.save(userCreateRequestDto.toEntity());
	}

	@Override
	public List<UserFindResponseDTO> getAllUser() {
		return userRepository.findAll().stream()
			.map(UserFindResponseDTO::new)
			.collect(Collectors.toList());
	}

	@Override
	public UserFindResponseDTO getUserById(int id) {
		User user = userRepository.findById(id).orElseThrow();

		return new UserFindResponseDTO(user);
	}

	@Override
	public UserInfoResponseDTO getUserInfoById(int id) {
		User user = userRepository.findById(id).orElseThrow();

		return new UserInfoResponseDTO(user);
	}

	@Override
	public void update(int id, UserUpdateRequestDTO userUpdateRequestDTO) {
		User user = userRepository.findById(id).orElseThrow();

		user.updateInfo(
			userUpdateRequestDTO.getPassword(),
			userUpdateRequestDTO.getName(),
			userUpdateRequestDTO.getEmail()
		);

		userRepository.update(id, user);
	}

	@Override
	public SessionUser checkLogin(UserLoginRequestDTO userLoginRequestDTO) {
		User user = userRepository.findByUserId(userLoginRequestDTO.getUserId()).orElseThrow();

		if (!user.getPassword().equals(userLoginRequestDTO.getPassword())) {
			throw new IllegalLoginException("잘못된 id와 password 입니다.");
		}

		return new SessionUser(user);
	}
}
