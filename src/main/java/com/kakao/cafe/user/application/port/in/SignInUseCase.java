package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.domain.Password;
import com.kakao.cafe.user.domain.UserId;

public interface SignInUseCase {

    FoundUserDto signIn(UserId userId, Password password);

}
