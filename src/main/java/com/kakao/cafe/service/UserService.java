package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
//import com.kakao.cafe.vo.User;

import java.util.List;

public interface UserService {
    void join(UserRegistrationDto userDto);

    List<User> getUsers();

    User findById(String id);

}
