package com.kakao.cafe.application.user.service;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UsersInfo;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import com.kakao.cafe.application.user.port.out.GetUserInfoPort;
import org.springframework.stereotype.Service;

@Service
public class GetUserInfoService implements GetUserInfoUseCase {

    private final GetUserInfoPort getUserInfoPort;

    public GetUserInfoService(
        GetUserInfoPort getUserInfoPort) {
        this.getUserInfoPort = getUserInfoPort;
    }

    @Override
    public UsersInfo getAllUsersInfo() {
        return getUserInfoPort.getAllUsersInfo();
    }

    @Override
    public UserInfo getUserProfile(String userId) {
        return getUserInfoPort.findUserByUserId(userId);
    }
}
