package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.*;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getUsers();

    void addUser(UserId userId, Password password, Name name, Email email);

    Optional<User> findUserById(UserId userId);

    int getSize();

    void update(UserId userId, Name name, Email email);
}
