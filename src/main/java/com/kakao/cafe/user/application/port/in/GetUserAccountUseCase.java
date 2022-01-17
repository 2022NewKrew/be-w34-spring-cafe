package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.application.dto.command.UserAccountDetailEmailCommand;
import com.kakao.cafe.user.application.dto.command.UserAccountDetailIdCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailListResult;
import com.kakao.cafe.user.application.dto.result.UserAccountDetailResult;

public interface GetUserAccountUseCase {

    UserAccountDetailResult getUserInfoByEmail(UserAccountDetailEmailCommand command);

    UserAccountDetailListResult getAllUser();

    UserAccountDetailResult getUserInfo(UserAccountDetailIdCommand command);
}
