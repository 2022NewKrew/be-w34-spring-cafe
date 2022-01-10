package com.kakao.cafe.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.request.UserCreateRequestDto;
import com.kakao.cafe.dto.response.UserFindResponseDto;
import com.kakao.cafe.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public void save(UserCreateRequestDto userCreateRequestDto) {
		userRepository.save(userCreateRequestDto.toDomain());
	}

	@Override
	public List<UserFindResponseDto> getAllUser() {
		List<UserFindResponseDto> userFindList = new ArrayList<>();

		for (User user : userRepository.findAll()) {
			userFindList.add(
				UserFindResponseDto.builder()
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
	public UserFindResponseDto getUserById(int id) {
		User user = userRepository.findById(id);

		return UserFindResponseDto.builder()
			.id(user.getId())
			.userId(user.getUserId())
			.name(user.getName())
			.email(user.getEmail())
			.build();
	}
}
