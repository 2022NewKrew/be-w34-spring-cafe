package com.kakao.cafe.service;

import com.kakao.cafe.domain.User;
import org.springframework.lang.NonNull;

public interface UserService {
    boolean add(@NonNull final User user);
}
