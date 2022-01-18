package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User create(SignUp signUp);
    List<User> list();
    Optional<User> getById(long id);
    Optional<User> getByUserId(String userId);
    Optional<User> login(String userId, String password);
    void updateUserId(long id, String userId);
    void updatePassword(long id, String password);
    void updateName(long id, String name);
    void updateEmail(long id, String email);
}
