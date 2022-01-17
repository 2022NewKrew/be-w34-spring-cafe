package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.user.*;
import com.kakao.cafe.domain.entity.User;

import java.util.List;

public interface UserRepository {
    void store(UserCreateCommand ucc);
    void modify(String userId,UserModifyCommand umc);
    void delete(String userId);
    User search(String userId);
    List<UserListShow> getAllUser();

    default UserInfo getUserInfo(String userId) {
        return new UserInfo(search(userId));
    }

    default UserProfileInfo getUserProfile(String userId) {
        return new UserProfileInfo(search(userId));
    }
}
