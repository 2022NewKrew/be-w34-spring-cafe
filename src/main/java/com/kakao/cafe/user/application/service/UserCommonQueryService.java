package com.kakao.cafe.user.application.service;

import com.kakao.cafe.user.application.port.in.UserCommonQueryUseCase;
import com.kakao.cafe.user.application.port.in.UserInventoryInfo;
import com.kakao.cafe.user.application.port.out.UserCommonQueryPort;

import java.util.List;

public class UserCommonQueryService implements UserCommonQueryUseCase {
    private final UserCommonQueryPort userCommonQueryPort;

    public UserCommonQueryService(UserCommonQueryPort userCommonQueryPort) {
        this.userCommonQueryPort = userCommonQueryPort;
    }

    @Override
    public List<UserInventoryInfo> findUserInventoryInfo() {
        return userCommonQueryPort.findUserInventoryInfo();
    }
}
