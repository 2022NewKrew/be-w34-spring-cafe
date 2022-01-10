package com.kakao.cafe.module.service;

import com.kakao.cafe.module.model.domain.User;
import com.kakao.cafe.module.model.dto.UserDto;
import com.kakao.cafe.module.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private Long autoIncrementId = 0L;

    public void signUp(UserDto userDto) {
        User user = userDto.toUser(autoIncrementId++);
        userRepository.addUser(user);
    }

    public List<UserDto> userList() {
        return userRepository.findAllUsers().stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findUser(Long sn) {
        return UserDto.toDto(userRepository.findUser(sn));
    }
}
