package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.UserInfo;
import com.kakao.cafe.repository.user.DbUserRepository;
import com.kakao.cafe.repository.user.MemoryUserRepository;
import com.kakao.cafe.repository.user.UserDao;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import com.kakao.cafe.web.dto.user.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserDao userDao) {
        userRepository= new DbUserRepository(userDao);
    }

    public void userSingUp(UserCreateRequestDto userCreateRequestDto) {
        userRepository.create(UserInfo.builder(userCreateRequestDto).build());
    }

    public List<UserResponseDto> findAll() {
        return userRepository.readAll().stream().map(userInfo ->
                UserResponseDto.builder()
                        .userId(userInfo.getUserId())
                        .email(userInfo.getEmail())
                        .signUpDate(userInfo.getSignUpDate())
                        .name(userInfo.getName())
                        .build()).collect(Collectors.toList());
    }

    public UserResponseDto findById(String id) {
        Optional<UserInfo> userInfoOptional = userRepository.read(id);
        UserInfo userInfo = userInfoOptional.orElseThrow(() -> new IllegalArgumentException("Illegal Params: User ID"));
        return UserResponseDto.builder()
                .userId(userInfo.getUserId())
                .email(userInfo.getEmail())
                .signUpDate(userInfo.getSignUpDate())
                .name(userInfo.getName())
                .build();
    }
}
