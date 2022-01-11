package com.kakao.cafe.user;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:38
 */
public interface UserRepository {

    User save(User user);
    List<User> findAll();
    User findUserByUserId(String userId);
}
