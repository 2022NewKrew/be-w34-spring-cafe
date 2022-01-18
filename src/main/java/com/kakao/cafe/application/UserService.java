package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.interfaces.common.UserDto;
import com.kakao.cafe.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void signup(UserDto userDto) {
        userRepository.add(userMapper.toEntity(userDto));
    }

    public void update(User user, UserDto userDto) {
        user.setNickname(userDto.getNickname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.update(user);
    }

    public User login(UserDto userDto) {
        return userRepository.findByUserIdAndPassword(userDto.getUserId(), userDto.getPassword())
                .orElseThrow(NoSuchElementException::new);
    }
}
