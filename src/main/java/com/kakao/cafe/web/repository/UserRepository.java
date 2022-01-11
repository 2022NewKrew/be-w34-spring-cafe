package com.kakao.cafe.web.repository;

import com.kakao.cafe.web.domain.User;
import com.kakao.cafe.web.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(UserDTO userDTO);
    List<User> getUserList();
    Optional<User> getUserById();
}
