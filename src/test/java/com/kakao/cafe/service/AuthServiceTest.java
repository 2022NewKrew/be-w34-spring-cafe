package com.kakao.cafe.service;

import com.kakao.cafe.dto.UserLoginDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.repository.JdbcUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    private final String userId = "chen";
    private final String password = "1234";

    @Autowired
    JdbcUserRepository jdbcUserRepository;

    @Test
    @DisplayName("[성공] AuthService 생성")
    void AuthService() {
        new AuthService(jdbcUserRepository);
    }

    @Test
    @DisplayName("[성공] 로그인 성공해서 user 객체 return")
    void login() {
        // given
        AuthService authService = new AuthService(jdbcUserRepository);
        UserLoginDto userLoginDto = new UserLoginDto(userId, password);

        // when
        User login = authService.login(userLoginDto);

        // then
        Assertions.assertEquals("chen", login.getUserId());
        Assertions.assertEquals("1234", login.getPassword());
    }

    @Test
    @DisplayName("[실패] 로그인 닉네임 실패")
    void login_Nickname_Fail() {

    }

    @Test
    @DisplayName("[성공] 로그인 성공해서 user 객체 return")
    void login_Password_Fail() {

    }
}