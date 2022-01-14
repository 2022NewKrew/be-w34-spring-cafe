package com.kakao.cafe.infra.dao;

import com.kakao.cafe.web.user.form.ModifyingUserInfo;
import com.kakao.cafe.web.user.form.UserProfileInfo;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void saveUser(UserCreateCommand userCreateCommand);
    Optional<UserCreateCommand> findUserById(Long userId);
    List<UserCreateCommand> findAll();
    void updateUser(Long userId, String name, String email, String password);

    Optional<UserProfileInfo> findUserProfileInfo(Long userId);

    Optional<ModifyingUserInfo> findModifyingUserForm(Long userId);

    Optional<UserLoginCommand> findPasswordAndIdByNickname(String nickname);

    Optional<String> findNicknameByExpectedNickname(String nickname);
}
