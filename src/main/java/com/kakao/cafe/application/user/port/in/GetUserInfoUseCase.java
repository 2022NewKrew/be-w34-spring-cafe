package com.kakao.cafe.application.user.port.in;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UsersInfo;

public interface GetUserInfoUseCase {

    UsersInfo getAllUsersInfo();

    UserInfo getUserProfile(String userId);
}
