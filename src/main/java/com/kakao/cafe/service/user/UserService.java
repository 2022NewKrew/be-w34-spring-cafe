package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserDto;

import java.util.List;

public interface UserService {
    void addUser(UserDto userdto);
    List<User> findUsers();
    User findUserById(Long id);
}
