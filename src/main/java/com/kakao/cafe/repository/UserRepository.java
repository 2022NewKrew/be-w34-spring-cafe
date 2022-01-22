package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import java.util.List;

public interface UserRepository {

    void createUser(User user);
    boolean isUsernameUsed(String username);
    List<User> findAllUsers();
    User findByUsername(String username);
    User updateUser(User user);
}
