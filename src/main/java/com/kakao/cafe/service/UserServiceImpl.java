package com.kakao.cafe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.cafe.dto.request.UserCreateRequestDTO;
import com.kakao.cafe.dto.request.UserLoginRequestDTO;
import com.kakao.cafe.dto.request.UserUpdateRequestDTO;
import com.kakao.cafe.dto.response.UserFindResponseDTO;
import com.kakao.cafe.dto.response.UserInfoResponseDTO;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.exception.IllegalLoginException;
import com.kakao.cafe.repository.UserRepository;

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
		List<UserFindResponseDTO> userFindList = new ArrayList<>();

		for (User user : userRepository.findAll()) {
			userFindList.add(
				UserFindResponseDTO.builder()
					.id(user.getId())
					.userId(user.getUserId())
					.name(user.getName())
					.email(user.getEmail())
					.build()
			);
		}

		return userFindList;
	}

	@Override
	public UserFindResponseDTO getUserById(int id) {
		User user = userRepository.findById(id);

		return UserFindResponseDTO.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.name(user.getName())
			.email(user.getEmail())
			.build();
	}

	@Override
	public UserInfoResponseDTO getUserInfoById(int id) {
		User user = userRepository.findById(id);

		return UserInfoResponseDTO.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.password(user.getPassword())
			.name(user.getName())
			.email(user.getEmail())
			.build();
	}

	@Override
	public void update(int id, UserUpdateRequestDTO userUpdateRequestDTO) {
		User user = userRepository.findById(id);
		user.updateInfo(
			userUpdateRequestDTO.getPassword(),
			userUpdateRequestDTO.getName(),
			userUpdateRequestDTO.getEmail()
		);
		userRepository.update(id, user);
	}

	@Override
	public UserInfoResponseDTO checkLogin(UserLoginRequestDTO userLoginRequestDTO) {
		List<User> userList = userRepository.findByUserIdAndPassword(
			userLoginRequestDTO.getUserId(),
			userLoginRequestDTO.getPassword()
		);

		if (userList.size() == 0) {
			throw new IllegalLoginException("잘못된 id와 password 입니다.");
		}

		User user = userList.get(0);

		return UserInfoResponseDTO.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.password(user.getPassword())
			.name(user.getName())
			.email(user.getEmail())
			.build();
	}
}
