package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.UserEntity;
import com.kakao.cafe.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByNickName(String nickName);

    UserEntity findByEmail(String email);
}
