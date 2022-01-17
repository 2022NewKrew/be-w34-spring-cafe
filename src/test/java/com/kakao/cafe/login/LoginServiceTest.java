package com.kakao.cafe.login;

import com.kakao.cafe.controller.dto.UserJoinForm;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.login.dto.UserLogin;
import com.kakao.cafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
        User from = User.from(form);
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
