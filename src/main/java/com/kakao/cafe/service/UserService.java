package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.dto.UserRegisterRequest;
import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
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
        return new UserProfileDto(
                user.getId(),
                user.getName(),
                user.getEmail());
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
