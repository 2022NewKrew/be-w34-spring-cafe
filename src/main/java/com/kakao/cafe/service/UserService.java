package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.dto.user.UserRequestDto;
import com.kakao.cafe.dto.user.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User join(UserRequestDto userRequestDto) {
        if (checkDuplicateUserId(userRequestDto.getUserId())) {
            throw new IllegalArgumentException("이미 등록된 사용자 입니다.");
        }

        User user = User.builder()
                .userId(userRequestDto.getUserId())
                .password(userRequestDto.getPassword())
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .build();

        return userRepository.save(user);
    }

    public UserResponseDto findProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자 입니다."));

        return new UserResponseDto(user);
    }

    public List<UserResponseDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public boolean checkDuplicateUserId(String userId) {
        return userRepository.findById(userId).isPresent();
    }
}
