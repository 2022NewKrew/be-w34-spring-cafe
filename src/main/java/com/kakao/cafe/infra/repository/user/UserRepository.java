package com.kakao.cafe.infra.repository.user;

import com.kakao.cafe.web.user.form.ModifyingUserInfo;
import com.kakao.cafe.web.user.form.UserProfileInfo;
import com.kakao.cafe.infra.dao.UserCreateCommand;
import com.kakao.cafe.web.user.form.UserInventoryInfo;

import java.util.List;

public interface UserRepository {
    void saveUser(UserCreateCommand userCreateCommand);

    void updateUser(Long userId, String name, String email, String password);

    List<UserInventoryInfo> findUserInventoryInfo();

    UserProfileInfo findUserProfileInfo(Long userId);

    ModifyingUserInfo findModifyingUserForm(Long userId);

    String findPasswordAndIdByNickname(String nickname);

    Boolean isAlreadyExistNickname(String nickname);
}
