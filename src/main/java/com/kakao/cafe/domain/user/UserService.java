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
    public UserResponseDto loginUser(UserLoginRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UserLoginFailedException("아이디 또는 비밀번호가 틀립니다. 다시 로그인 해주세요."));
        if(user.matchesPassword(password)) {
            return new UserResponseDto(user);
        }
        throw new UserLoginFailedException("아이디 또는 비밀번호가 틀립니다. 다시 로그인 해주세요.");
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
