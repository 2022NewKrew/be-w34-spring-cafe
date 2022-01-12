package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.interfaces.common.UserDto;
import com.kakao.cafe.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> findAllUser() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public Optional<UserDto> findById(long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        return Optional.of(userMapper.toDto(user));
    }

    public void signup(UserDto userDto) {
        userRepository.add(userMapper.toEntity(userDto));
    }
}
