package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.UserJoinDto;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll(Integer pageNum, Integer pageSize);

    boolean existsById(Long id);

    User join(UserJoinDto userJoinDTO);
}
