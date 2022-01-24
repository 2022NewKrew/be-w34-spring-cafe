package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleUserForm;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(SampleUserForm form);
    void update(User user);
    Optional<User> findByUserID(String uID);
    Optional<User> findByNumID(Long ID);
    List<User> findAll();
}
