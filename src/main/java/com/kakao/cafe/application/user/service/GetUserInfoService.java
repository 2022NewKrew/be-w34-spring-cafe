package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.application.user.port.out.GetUserEntityPort;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public class GetUserInfoService implements GetUserInfoUseCase {

    private final GetUserEntityPort getUserEntityPort;

    public GetUserInfoService(GetUserEntityPort getUserEntityPort) {
        this.getUserEntityPort = getUserEntityPort;
    }

    @Override
    public UserInfoList getAllUsersInfo() {
        return getUserEntityPort.getAllUsersInfo();
    }

    @Override
    public UserInfo getUserProfile(String userId) throws UserNotExistException {
        return UserInfo.from(getUserEntityPort.findUserByUserId(userId));
    }
}
