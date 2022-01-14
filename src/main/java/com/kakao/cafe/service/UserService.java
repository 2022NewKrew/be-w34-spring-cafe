package com.kakao.cafe.service;

import com.kakao.cafe.controller.UserDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public UserDto findByUserId(String userId) {
        return UserDto.from(userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디")));
    }

    public int update(String userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디"));
        validPassword(userDto, user);
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        return userRepository.update(user);
    }

    public int create(UserDto userDto) {
        User user = userDto.toEntity();
        validDuplicateUser(user);
        return userRepository.save(user);
    }

    private void validPassword(UserDto userDto, User user) {
        if (userDto.getPassword() != user.getPassword()) {
            throw new IllegalStateException("패스워드 불일치");
        }
    }

    private void validDuplicateUser(User user) {
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalStateException("해당 아이디 이미 존재");
        }
    }
}
