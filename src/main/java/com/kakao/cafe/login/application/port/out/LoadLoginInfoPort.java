package com.kakao.cafe.login.application.port.out;

import com.kakao.cafe.login.domain.LoginUser;

import java.util.Optional;

public interface LoadLoginInfoPort {
    Optional<LoginUser> findLoginInfo(String nickname);
}
