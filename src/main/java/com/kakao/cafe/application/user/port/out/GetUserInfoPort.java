package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public interface GetUserInfoPort {

    UserInfoList getAllUsersInfo();

    UserInfo findUserByUserId(String userId) throws UserNotExistException;
}
