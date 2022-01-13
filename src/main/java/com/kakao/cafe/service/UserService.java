package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserResponseDto;
import com.kakao.cafe.dto.UserRequestDto;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create(UserRequestDto userRequestDto) {
        userRepository.save(userRequestDto);
    }

    public void update(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userRepository.findByUserId(userRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        userRepository.update(userResponseDto.getId(), userRequestDto);
    }

    public Optional<UserResponseDto> read(String userId) {
        return userRepository.findByUserId(userId);
    }

    public List<UserResponseDto> readAll() {
        return userRepository.findAll();
    }
}
