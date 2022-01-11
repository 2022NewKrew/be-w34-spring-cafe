package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpReq;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(SignUpReq req);

    Optional<User> findByUserId(String userId);

    List<User> findAll();
}
