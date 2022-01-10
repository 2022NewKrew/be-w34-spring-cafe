package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserService {
    void add(@NonNull final User user);
    List<User> getList();
}
