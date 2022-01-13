package com.kakao.cafe.repo;

import com.kakao.cafe.domain.User;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRepository {
    boolean add(@NonNull final User user);

    boolean checkIdExist(@NonNull final String id);

    User findById(@NonNull final String id);

    List<User> getList();
}
