package com.kakao.cafe.adapter.out.infra.persistence.user;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {

    void save(UserInfoEntity userInfoEntity);

    List<UserInfoEntity> getAllUserList();

    Optional<UserInfoEntity> findByUserId(String userId);
}
