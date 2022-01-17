package com.kakao.cafe.repository.user;

import com.kakao.cafe.domain.user.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void create(UserInfo userInfo);

    Optional<UserInfo> read(String id);

    List<UserInfo> readAll();
}
