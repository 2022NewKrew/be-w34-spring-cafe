package com.kakao.cafe.dao.user;

import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> getUsers();

    void addUser(User user);

    Optional<User> findUserById(UserId userId);

    int getSize();

    void update(User user);
}
