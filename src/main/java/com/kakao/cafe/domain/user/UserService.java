package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dto.UserCreateRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import com.kakao.cafe.domain.user.dto.UserUpdateRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> retrieveAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    public UserResponseDto retrieveUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    public Long updateUser(String userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        user.updateProfile(new Profile(requestDto.getName(), requestDto.getEmail()), requestDto.getPassword());
        return userRepository.save(user);
    }

    public Long createUser(UserCreateRequestDto requestDto) {
        return userRepository.save(requestDto.toUser());
    }
}
