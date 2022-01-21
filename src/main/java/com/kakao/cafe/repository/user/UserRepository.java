package com.kakao.cafe.repository.user;

import com.kakao.cafe.model.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Optional<User> findById(UUID id);

    Optional<User> findByUserId(String userId);
}
