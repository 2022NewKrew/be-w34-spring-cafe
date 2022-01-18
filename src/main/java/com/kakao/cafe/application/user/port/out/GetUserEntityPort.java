package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public interface GetUserEntityPort {

    UserInfoList getAllUsersInfo();

    User findUserByUserId(String userId) throws UserNotExistException;
}
