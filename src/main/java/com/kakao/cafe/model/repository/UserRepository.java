package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean insertUser(UserDTO userDTO);
    List<User> selectAllUsers();
    Optional<User> selectUserById(Long id);
    Optional<User> selectUserByUserId(String userId);
    Optional<User> selectUserByLoginInfo(String userId, String password);
    boolean updateUser(UserDTO userDTO);
    boolean deleteUser(String userId, String password);
}
