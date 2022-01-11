package com.kakao.cafe.repository;

import com.kakao.cafe.vo.User;
import com.kakao.cafe.vo.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(UserDto userDto);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();
}
