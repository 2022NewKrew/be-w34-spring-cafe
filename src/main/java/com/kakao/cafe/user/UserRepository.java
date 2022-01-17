package com.kakao.cafe.user;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:38
 */
public interface UserRepository {

    User insert(User user);
    User update(User user);
    List<User> findAll();
    User findUserById(Integer id);

    default User findUserByUserId(String userId) {
        return null;
    }
}
