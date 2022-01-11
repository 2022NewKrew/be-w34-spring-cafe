package com.kakao.cafe.user;

import java.util.List;

public interface UserService {
    Long save(User user);

    User findOne(Long id);

    List<User> findAll();

    boolean update(User user);
}
