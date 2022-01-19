package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserId;

import java.util.List;

public interface UserRepository {
    void save(User user);
    void update(UserId id, Password password, User user);
    User findById(UserId id);
    User findByEmail(Email email);
    List<User> findAll();
}
