package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;

public interface UserRepository {

    void save(User user);

    User findByUserId(String userId);

    List<User> findAll();

    void deleteAll();
}
