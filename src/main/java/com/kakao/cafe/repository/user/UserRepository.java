package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(UserDto userDto);
    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();
}
