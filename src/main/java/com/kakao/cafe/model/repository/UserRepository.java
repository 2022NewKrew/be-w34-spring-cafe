package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.User;
import com.kakao.cafe.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean insertUser(UserDTO userDTO);
    List<UserDTO> selectAllUsers();
    Optional<UserDTO> selectUserById(Long id);
    Optional<UserDTO> selectUserByUserId(String userId);
    Optional<UserDTO> selectUserByLoginInfo(String userId, String password);
    boolean updateUser(UserDTO userDTO);
    boolean deleteUser(String userId, String password);
}
