package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> save(User user);

    Optional<User> find(String userId);

    List<User> findAll();
}
