package com.kakao.cafe.user.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User save(User user);

    List<User> findAll();

    User findByIdOrNull(String userId);

    boolean existsById(String userId);

    User findByUserNameOrNull(String userName);
}
