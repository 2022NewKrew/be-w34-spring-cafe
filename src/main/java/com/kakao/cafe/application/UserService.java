package com.kakao.cafe.application;


import com.kakao.cafe.domain.user.User;

import java.util.List;

public interface UserService {
    void join(User user);
    List<User> findAllUser();
}
