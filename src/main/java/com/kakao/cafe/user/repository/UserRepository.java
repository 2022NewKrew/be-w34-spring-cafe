package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findByUserId(String userId);

    List<User> findAll();

    void deleteAll();
}
