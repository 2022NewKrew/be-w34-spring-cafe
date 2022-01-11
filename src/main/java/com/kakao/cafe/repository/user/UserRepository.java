package com.kakao.cafe.repository.user;

import com.kakao.cafe.entity.UserEntity;
import com.kakao.cafe.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByNickName(String nickName);
}
