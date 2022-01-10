package com.kakao.cafe.user;

import java.util.List;

public interface UserRepository {
    Long save(User user);

    User findOne(Long id);

    List<User> findAll();
}
