package com.kakao.cafe.web.user.repository;

import com.kakao.cafe.web.user.domain.User;
import com.kakao.cafe.web.user.dto.UserCreateDTO;

import java.util.List;

public interface UserRepository {
    User save(UserCreateDTO userDTO);
    List<User> getUserList();
    User getUserByUserId(String userId);
    User update(UserCreateDTO userUpdateDTO);
}
