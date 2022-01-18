package com.kakao.cafe.user.application.port.in;

public interface UserUpdateUseCase {
    void updateUser(Long userId, ModifyingUserInfo modifyingUserInfo);

    ModifyingUserInfo findModifyingUserForm(Long userId);
}
