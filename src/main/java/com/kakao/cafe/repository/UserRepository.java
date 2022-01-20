package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleUserForm;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean addUser(SampleUserForm form);
    User findUser(String userID);
    boolean updateUser(SampleUserForm form);
    boolean checkUser(String userID);
    List<User> getUserList();
}
