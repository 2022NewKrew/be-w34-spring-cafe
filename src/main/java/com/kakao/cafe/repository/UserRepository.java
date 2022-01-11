package com.kakao.cafe.repository;

import com.kakao.cafe.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void add(User user);

    List<User> findAllUsers();

    Optional<User> findUserByUserId(String userId);
}
