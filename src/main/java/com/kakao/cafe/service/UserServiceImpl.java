package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.JdbcUserRepository;
import com.kakao.cafe.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final JdbcUserRepository jdbcUserRepository;

    public UserServiceImpl(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public void join(UserRegistrationDto userDto) {
        jdbcUserRepository.createUser(userDto.toEntity());
    }

    @Override
    public List<User> getUsers() {
        return jdbcUserRepository.readUsers();
    }

    @Override
    public User findById(String userId) {
        Optional<User> user = jdbcUserRepository.readUser(userId);
        return user.orElseThrow(() -> new RuntimeException("user 조회 null 검증"));
//        return jdbcUserRepository.readUser(userId);
    }
}
