package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
import com.kakao.cafe.domain.UserMapper;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final UserMapper MAPPER = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signup(UserDto userDto) throws IllegalArgumentException {
        userDto.validate();
        validateDuplicatedUserId(userDto.getUserId());
        userRepository.createUser(userDto);
    }

    private void validateDuplicatedUserId(String userId) {
        if (userRepository.isUserIdUsed(userId)) {
            throw new IllegalArgumentException("[ERROR] 이미 사용중인 아이디입니다.");
        }
    }

    public List<UserDto> getUsers() {
        return userRepository.findAllUsers().stream()
            .map(MAPPER::toUserDto)
            .collect(Collectors.toList());
    }

    public UserDto getUserByUserId(String userId) {
        if (!userRepository.isUserIdUsed(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 사용자를 찾을 수 없습니다.");
        }
        User user = userRepository.findUserByUserId(userId);
        return MAPPER.toUserDto(user);
    }

    public UserDto updateUser(UserDto userDto) {
        userDto.validate();
        if (!userRepository.isUserIdUsed(userDto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[ERROR] 사용자를 찾을 수 없습니다.");
        }
        String uid = userRepository.findUidByUserId(userDto.getUserId());
        User user = MAPPER.toUserEntity(userDto, uid);
        User updatedUser = userRepository.updateUser(user);
        return MAPPER.toUserDto(updatedUser);
    }
}
