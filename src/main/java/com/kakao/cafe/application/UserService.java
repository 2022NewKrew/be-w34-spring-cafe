package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.exception.NoSuchUserException;
import com.kakao.cafe.interfaces.common.UserDto;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .stream()
                .findAny()
                .orElseThrow(NoSuchUserException::new);
    }

    public void signup(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
    }

    public void update(User user, UserDto userDto) {
        user.update(userDto.getEmail(), userDto.getNickname());
        userRepository.save(user);
    }

    public User login(UserDto userDto) {
        return userRepository.findByUserIdAndPassword(userDto.getUserId(), userDto.getPassword())
                .stream()
                .findAny()
                .orElseThrow(NoSuchUserException::new);
    }
}
