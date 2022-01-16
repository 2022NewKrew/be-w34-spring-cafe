package com.kakao.cafe.user.application.port.out;

import com.kakao.cafe.user.domain.User;

public interface SaveUserPort {

    void save(User user);

}
