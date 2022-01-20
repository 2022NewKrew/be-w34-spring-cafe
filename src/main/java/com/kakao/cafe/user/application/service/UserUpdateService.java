package com.kakao.cafe.user.application.service;

import com.kakao.cafe.common.exception.UserUpdateException;
import com.kakao.cafe.user.application.port.in.ModifyingUserRequest;
import com.kakao.cafe.user.application.port.in.ModifyingUserResult;
import com.kakao.cafe.user.application.port.in.UserUpdateUseCase;
import com.kakao.cafe.user.application.port.out.UserUpdatePort;
import com.kakao.cafe.user.domain.UpdateUser;

public class UserUpdateService implements UserUpdateUseCase {
    private final UserUpdatePort userUpdatePort;

    public UserUpdateService(UserUpdatePort userUpdatePort) {
        this.userUpdatePort = userUpdatePort;
    }

    @Override
    public void updateUser(Long userId, ModifyingUserRequest modifyingUserRequest) throws UserUpdateException {
        UpdateUser updateUser = userUpdatePort.findUpdateUser(userId);
        updateUser.validatePassword(modifyingUserRequest.getPassword());
        userUpdatePort.updateUser(userId,
                modifyingUserRequest.getName(),
                modifyingUserRequest.getEmail(),
                modifyingUserRequest.getPassword());
    }

    @Override
    public ModifyingUserResult findModifyingUserForm(Long userId) {
        return userUpdatePort.findModifyingUserForm(userId);
    }
}
