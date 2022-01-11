package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UsersInfo;

public interface GetUserInfoPort {

    UsersInfo getAllUsersInfo();

    UserInfo findUserByUserId(String userId);
}
