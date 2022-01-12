package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.model.User;
import java.util.List;

public interface UserRepository {

    void save(User user);

    List<User> findAll();

    Long getNumberOfUsers();

    User findOneByUserId(String userId);
}
