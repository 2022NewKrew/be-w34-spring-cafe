package com.kakao.cafe.module.service;

import com.kakao.cafe.infra.exception.DuplicateNameException;
import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.model.dto.UserDtos;
import com.kakao.cafe.module.model.mapper.UserMapper;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.kakao.cafe.module.model.dto.UserDtos.*;
import static com.kakao.cafe.module.model.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private Long autoIncrementId = 0L;

    public void signUp(UserSignUpDto userSignUpDto) {
        validateDuplicateName(userSignUpDto.getName());
        User user = toUser(autoIncrementId++, userSignUpDto.getUserId(), userSignUpDto.getPassword(),
                userSignUpDto.getName(), userSignUpDto.getEmail());
        userRepository.addUser(user);
    }

    public List<UserDto> userList() {
        return userRepository.findAllUsers().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto findUser(Long id) {
        return toUserDto(userRepository.findUserById(id));
    }

    public void updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findUserById(id);
        validatePassword(user.getPassword(), userUpdateDto.getPassword());
        user.update(userUpdateDto.getNewPassword(), userUpdateDto.getName(), userUpdateDto.getEmail());
    }

    private void validateDuplicateName(String name) {
        userRepository.findUserByName(name)
                .ifPresent(e -> {
                    throw new DuplicateNameException("이미 존재하는 이름입니다.");
                });
    }

    private void validatePassword(String password, String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
