package com.kakao.cafe.application.user.port.in;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public interface GetUserInfoUseCase {

    UserInfoList getAllUsersInfo();

    UserInfo getUserProfile(String userId) throws UserNotExistException;
}
