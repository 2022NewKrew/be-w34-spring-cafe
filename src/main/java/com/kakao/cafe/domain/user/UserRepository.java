package com.kakao.cafe.domain.user;

import java.util.List;

public interface UserRepository {
    void save(User user);
    void update(String id, String password, User user);
    User findById(String id);
    List<User> findAll();
}
