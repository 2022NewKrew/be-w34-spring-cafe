package com.kakao.cafe.service;

import com.kakao.cafe.dto.LoginRequest;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.exception.LoginUserNotFoundException;
import com.kakao.cafe.exception.LoginWrongPasswordException;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository jdbcUserRepository) {
        this.userRepository = jdbcUserRepository;
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void register(UserRegisterRequest requestDto) {
        userRepository.findByUserId(requestDto.getUserId())
                .ifPresent(user -> {
                    throw new CustomException(ErrorCode.USERID_DUPLICATION);
                });
        userRepository.save(requestDto.toEntity());
    }

    public UserProfileDto getUserProfileById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return UserProfileDto.of(user);
    }

    public User login(LoginRequest requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new LoginUserNotFoundException(requestDto.getUserId()));

        if (!user.match(requestDto.getPassword())) {
            throw new LoginWrongPasswordException();
        }

        return user;
    }
}
