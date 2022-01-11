package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean insertUser(User user);
    List<User> selectAllUsers();
    Optional<User> selectUserByID(String id);
    Optional<User> selectUserByLoginInfo(String id, String password);
    boolean updateUser(User user);
    boolean deleteUser(String id, String password);
}
