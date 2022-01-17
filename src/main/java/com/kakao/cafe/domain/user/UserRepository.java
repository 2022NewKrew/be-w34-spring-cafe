package com.kakao.cafe.domain.user;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User findById(int id);
    User findByStringId(String stringId);
    List<User> findAll();
}
