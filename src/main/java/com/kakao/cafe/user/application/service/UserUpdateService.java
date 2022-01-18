package com.kakao.cafe.user.application.service;

import com.kakao.cafe.user.application.port.in.ModifyingUserInfo;
import com.kakao.cafe.user.application.port.in.UserUpdateUseCase;
import com.kakao.cafe.user.application.port.out.UserUpdatePort;

public class UserUpdateService implements UserUpdateUseCase {
    private final UserUpdatePort userUpdatePort;

    public UserUpdateService(UserUpdatePort userUpdatePort) {
        this.userUpdatePort = userUpdatePort;
    }

    @Override
    public void updateUser(Long userId, ModifyingUserInfo modifyingUserInfo) {
        userUpdatePort.updateUser(userId,
                modifyingUserInfo.getName(),
                modifyingUserInfo.getEmail(),
                modifyingUserInfo.getPassword());
    }

    @Override
    public ModifyingUserInfo findModifyingUserForm(Long userId) {
        return userUpdatePort.findModifyingUserForm(userId);
    }
}
