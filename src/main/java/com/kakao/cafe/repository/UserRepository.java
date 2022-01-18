package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
import com.kakao.cafe.util.Page;
import com.kakao.cafe.util.Pageable;

import java.util.Optional;

public interface UserRepository {
    public void save(User entity);

    public void update(User entity);

    public Optional<User> findByEmail(User entity);

    public Page<User> findAll(Pageable pageable);
}
