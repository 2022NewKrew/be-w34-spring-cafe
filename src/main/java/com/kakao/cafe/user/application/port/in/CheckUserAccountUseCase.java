package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.application.dto.command.UserAccountCheckCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountCheckResult;

public interface CheckUserAccountUseCase {

    UserAccountCheckResult checkPassword(UserAccountCheckCommand command);

}
