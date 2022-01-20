package com.kakao.cafe.user.application.port.in;

import com.kakao.cafe.user.domain.Email;
import com.kakao.cafe.user.domain.Password;

public interface LoginUseCase {

    FoundUserDto login(Email email, Password password);

}
