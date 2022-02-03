package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleUserForm;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(SampleUserForm form);
    void update(User user);
    User findByUserID(String uID);
    User findByNumID(Long ID);
    User checkMatchIDnPW(String uID, String PW);
    Boolean checkExistenceByUserID(String uID);
    List<User> findAll();
}
