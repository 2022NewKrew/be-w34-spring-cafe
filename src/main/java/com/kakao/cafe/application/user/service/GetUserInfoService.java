package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.application.user.port.out.GetUserInfoPort;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public class GetUserInfoService implements GetUserInfoUseCase {

    private final GetUserInfoPort getUserInfoPort;

    public GetUserInfoService(GetUserInfoPort getUserInfoPort) {
        this.getUserInfoPort = getUserInfoPort;
    }

    @Override
    public UserInfoList getAllUsersInfo() {
        return getUserInfoPort.getAllUsersInfo();
    }

    @Override
    public UserInfo getUserProfile(String userId) throws UserNotExistException {
        return getUserInfoPort.findUserByUserId(userId);
    }
}
