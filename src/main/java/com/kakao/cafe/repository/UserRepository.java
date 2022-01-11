package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    List<User> findAll();

    Optional<User> findByUserId(String userId);

    Optional<User> findByName(String writerName);
}
