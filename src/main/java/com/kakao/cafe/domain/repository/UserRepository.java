package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserRepository {

    @Nullable
    User create(SignUp signUp);
    List<User> list();
    @Nullable
    User getById(long id);
    @Nullable
    User getByUserId(String userId);
    @Nullable
    User login(String userId, String password);
}
