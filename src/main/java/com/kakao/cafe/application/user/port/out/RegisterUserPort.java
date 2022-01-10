package com.kakao.cafe.application.user.port.out;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Component;

@Component("registerUserPort")
public interface RegisterUserPort {

    void registerUser(User user);
}
