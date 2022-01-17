package com.kakao.cafe.adapter.out.infra.persistence.user;

import com.kakao.cafe.domain.user.User;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {

    void save(User user);

    void update(User user);

    List<User> getAllUserList();

    Optional<User> findByUserId(String userId);
}
