package com.kakao.cafe.aop;

import com.kakao.cafe.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    private final UserService userService;

    @Autowired
    public LoginAspect(UserService userService) {
        this.userService = userService;
    }

    @Before("@annotation(com.kakao.cafe.util.annotation.LoginCheck)")
    public void loginCheck() {

    }


}
