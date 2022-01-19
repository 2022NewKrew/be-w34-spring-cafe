package com.kakao.cafe.domain.user;

import com.kakao.cafe.domain.user.dto.UserCreateRequestDto;
import com.kakao.cafe.domain.user.dto.UserLoginRequestDto;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import com.kakao.cafe.domain.user.dto.UserUpdateRequestDto;
import com.kakao.cafe.domain.user.exception.UserLoginFailedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> retrieveAllUsers() {
        return userRepository.findAll().stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto retrieveUser(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto retrieveUserForUpdate(String userId, String currentUserId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        user.checkEqualsUser(currentUserId);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto loginUser(UserLoginRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserLoginFailedException("유저가 존재하지 않습니다."));
        user.checkMatchesPasswordForLogin(password);
        return new UserResponseDto(user);
    }

    @Transactional
    public Long updateUser(String userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        user.updateProfile(new Profile(requestDto.getName(), requestDto.getEmail()), requestDto.getConfirmPassword());
        user.updatePassword(requestDto.getNewPassword(), requestDto.getConfirmPassword());
        return userRepository.save(user);
    }

    @Transactional
    public Long createUser(UserCreateRequestDto requestDto) {
        return userRepository.save(requestDto.toUser());
    }
}
