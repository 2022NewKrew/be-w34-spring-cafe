package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.dto.SignUpDTO;
import com.kakao.cafe.user.dto.UpdateDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(SignUpDTO singUpDto);

    Optional<User> findByUserId(String userId);

    List<User> findAll();

    User update(UpdateDTO updateDto);
}
