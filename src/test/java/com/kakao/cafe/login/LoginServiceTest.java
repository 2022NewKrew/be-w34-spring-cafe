package com.kakao.cafe.login;

import com.kakao.cafe.domain.login.LoginService;
import com.kakao.cafe.domain.user.dto.UserJoinForm;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.login.dto.UserLogin;
import com.kakao.cafe.domain.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @BeforeEach
    public void before(){
        UserJoinForm form = new UserJoinForm();
        form.setEmail("asdf");
        form.setName("hello");
        form.setUserId("test");
        form.setPassword("password");
        User from = form.toUser();
        userRepository.save(from);
    }

    @Test
    void login() throws Exception {
        // given
        UserLogin loginInfo = new UserLogin();
        loginInfo.setPassword("password");
        loginInfo.setUserId("test");

        // then
        Assertions.assertDoesNotThrow(() -> loginService.login(loginInfo));
    }

}
