package com.kakao.cafe.module.service;

import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.model.dto.UserDto;
import com.kakao.cafe.module.model.mapper.UserMapper;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.kakao.cafe.module.model.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private Long autoIncrementId = 0L;

    public void signUp(UserDto userDto) {
        validateDuplicateName(userDto.getName());
        User user = toUser(autoIncrementId++, userDto.getUserId(), userDto.getPassword(), userDto.getName(), userDto.getEmail());
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

    private void validateDuplicateName(String name) {
        userRepository.findUserByName(name)
                .ifPresent(e -> {
                    throw new IllegalArgumentException("이미 존재하는 이름입니다.");
                });
    }
}
