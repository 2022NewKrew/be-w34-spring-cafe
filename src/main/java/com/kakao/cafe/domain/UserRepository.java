package com.kakao.cafe.domain;

import java.util.List;

public interface UserRepository {

    User save(User user);

    List<User> findAll();

    User findByUserId(String userId);
}
