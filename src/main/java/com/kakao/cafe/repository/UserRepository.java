package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.NotFoundException;

import java.util.List;

public interface UserRepository {

    void save(User user);
    List<User> findAll();
    User findById(Long id) throws NotFoundException;
    User findByEmail(String email) throws NotFoundException;
}
