package com.kakao.cafe.user.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    int save(User user);

    List<User> findAll();

    User findByIdOrNull(String userId);

    boolean existsById(String userId);

    void delete(User user);

    void update(User user);
}
