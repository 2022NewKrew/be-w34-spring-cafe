package com.kakao.cafe.persistence.repository;

import com.kakao.cafe.persistence.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    List<User> findAllUsers();

    Optional<User> findUserByUid(String uid);
}
