package com.kakao.cafe.domain.user;

import com.kakao.cafe.infra.repository.user.UserRepository;
import com.kakao.cafe.web.user.form.ModifyingUserInfo;

public class UserUpdateService {
    private final UserRepository userRepository;

    public UserUpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void updateUser(Long userId, ModifyingUserInfo modifyingUserInfo) {
        userRepository.updateUser(userId,
                modifyingUserInfo.getName(),
                modifyingUserInfo.getEmail(),
                modifyingUserInfo.getPassword());
    }
}
