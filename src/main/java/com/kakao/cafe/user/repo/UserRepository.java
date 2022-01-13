package com.kakao.cafe.user.repo;

import com.kakao.cafe.common.CrRepository;
import com.kakao.cafe.user.model.User;

import java.util.Optional;

public interface UserRepository extends CrRepository<User> {
    int getUserCount();

    Optional<User> fetchByUserId(String userId);
}
