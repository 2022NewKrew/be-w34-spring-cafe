package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    void save(User user);
    void update(String id, String password, User user);
    User findById(String id);
    List<User> findAll();
}
