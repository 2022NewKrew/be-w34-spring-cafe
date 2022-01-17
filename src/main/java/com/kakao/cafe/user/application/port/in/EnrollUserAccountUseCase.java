package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.application.dto.command.UserAccountEnrollCommand;
import com.kakao.cafe.user.application.dto.result.UserAccountEnrollResult;

public interface EnrollUserAccountUseCase {

    UserAccountEnrollResult enroll(UserAccountEnrollCommand command);
}
