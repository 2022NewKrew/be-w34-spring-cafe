package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserCreateDTO;

import java.util.List;

public interface UserRepository {
    User save(UserCreateDTO userDTO);
    List<User> getUserList();
    User getUserByUserId(String userId);
    User update(UserCreateDTO userUpdateDTO);
}
