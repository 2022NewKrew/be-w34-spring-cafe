package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Long getNumberOfUsers();

    Optional<User> findOneByUserId(String userId);

    void updateOne(User user);
}
