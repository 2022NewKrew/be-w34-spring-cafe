package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.dto.user.UserCreateRequest;
import com.kakao.cafe.dto.user.UserUpdateRequest;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserCreateRequest userCreateRequest) {
        User user = new User(
                userCreateRequest.getUsername(),
                userCreateRequest.getNickname(),
                userCreateRequest.getEmail(),
                userCreateRequest.getPassword()
        );
        userRepository.save(user);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 크루입니다."));
        return new UserDto(user);
    }

    public void update(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 크루입니다."));
        user.update(
                userUpdateRequest.getNickname(),
                userUpdateRequest.getEmail(),
                userUpdateRequest.getPassword(),
                userUpdateRequest.getNewPassword()
        );
        userRepository.update(user);
    }
}
