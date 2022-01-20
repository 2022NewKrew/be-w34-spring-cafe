package com.kakao.cafe.Repository;

import com.kakao.cafe.Domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void saveUser(User user);

    Optional<User> findByUserId(Long id);

    Optional<User> findByNickName(String nickName);

    Optional<User> findByEmail(String email);

    List<User> findAllUsers();

    void editUserInfo(Long userId, String newEmail, String newNickname);
}
