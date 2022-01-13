package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    void update(String uid, String name, String email);

    List<User> findAllUsers();

    Optional<User> findUserByUid(String uid);
}
