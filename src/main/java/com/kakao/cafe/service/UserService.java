package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserAuthDto;
import com.kakao.cafe.controller.dto.UserDto;
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

    public void update(String userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디"));
        validPassword(user, userDto.getPassword());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userRepository.update(user);
    }

    public void create(UserDto userDto) {
        User user = userDto.toEntity();
        userRepository.save(user);
    }

    public int login(UserAuthDto userAuthDto) {
        User user = userRepository.findByUserId(userAuthDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디"));
        validPassword(user, userAuthDto.getPassword());
        return user.getId();
    }

    private void validPassword(User user, String password) {
        if (!user.matchPassword(password)) {
            throw new IllegalStateException("패스워드 불일치");
        }
    }

}
