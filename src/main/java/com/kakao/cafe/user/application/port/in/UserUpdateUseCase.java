package com.kakao.cafe.user.application.port.in;

public interface UserUpdateUseCase {
    void updateUser(Long userId, ModifyingUserRequest modifyingUserRequest);

    ModifyingUserResult findModifyingUserForm(Long userId);
}
