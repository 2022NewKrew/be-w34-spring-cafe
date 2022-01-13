package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.UserCreateCommand;
import com.kakao.cafe.domain.dto.UserModifyCommand;
import com.kakao.cafe.domain.entity.User;

import java.util.List;

public interface UserRepository {
    void store(UserCreateCommand ucc);
    void modify(String userId,UserModifyCommand umc);
    void delete(String userId);
    User search(String userId);
    List<User> getAllUser();
}
