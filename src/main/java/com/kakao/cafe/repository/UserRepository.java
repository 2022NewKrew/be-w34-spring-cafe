package com.kakao.cafe.repository;

import com.kakao.cafe.dto.user.SignUpDTO;
import com.kakao.cafe.dto.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserDTO save(SignUpDTO signUpDTO);

    Optional<UserDTO> findById(Long id);

    Optional<UserDTO> findByUserId(String userId);

    List<UserDTO> findAll();

}