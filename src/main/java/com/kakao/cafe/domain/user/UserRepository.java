package com.kakao.cafe.domain.user;

import java.util.List;

public interface UserRepository {
    void save(User user) throws IllegalArgumentException;
    List<User> findAll();
    void deleteAll();
}
