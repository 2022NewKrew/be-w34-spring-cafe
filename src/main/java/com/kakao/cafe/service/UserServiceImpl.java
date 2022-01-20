package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.JdbcUserRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final JdbcUserRepository jdbcUserRepository;

    public UserServiceImpl(JdbcUserRepository jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }

    @Override
    public void join(UserRegistrationDto userDto) {
        jdbcUserRepository.create(userDto.toEntity());
    }

    @Override
    public List<User> getUsers() {
        return jdbcUserRepository.readAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = jdbcUserRepository.readById(id);
        return user.orElseThrow(() -> new NullPointerException("유저 ID가 없습니다"));
    }
}
