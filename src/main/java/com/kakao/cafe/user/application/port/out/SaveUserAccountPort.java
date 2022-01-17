package com.kakao.cafe.user.application.port.out;

import com.kakao.cafe.user.domain.UserAccount;

public interface SaveUserAccountPort {

    UserAccount save(UserAccount userAccount);
}
