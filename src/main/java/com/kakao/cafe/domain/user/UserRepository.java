package com.kakao.cafe.domain.user;

import java.util.List;

public interface UserRepository {
    void create(User user);
    User findById(String id);
    List<User> findAll();
}
