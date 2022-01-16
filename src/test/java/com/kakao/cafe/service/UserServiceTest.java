package com.kakao.cafe.service;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.dao.user.VolatilityUserStorage;
import com.kakao.cafe.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("UserService 테스트")
class UserServiceTest {
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    private void before() {
        userDao = mock(VolatilityUserStorage.class);
        userService = new UserService(userDao);
    }

    @DisplayName("유저 아이디와 매칭 되는 유저의 비밀번호와 동일한 비밀번호가 입력되었을 때 예외를 던지지 않는다.")
    @Test
    public void legalPassword() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";

        when(userDao.findUserById(userId))
                .thenReturn(Optional.of(new User(userId, password, name, email)));

        //when
        //then
        assertThatCode(() -> userService.updateUser(userId, password, name, email))
                .doesNotThrowAnyException();
    }

    @DisplayName("유저 아이디와 매칭 되는 유저의 비밀번호와 다른 비밀번호가 입력되었을 때 IllegalArgumentException을 던진다.")
    @Test
    public void illegalPassword() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String illegalPassword = "isIllegal";

        when(userDao.findUserById(userId))
                .thenReturn(Optional.of(new User(userId, password, name, email)));

        //when
        //then
        assertThatThrownBy(() -> userService.updateUser(userId, illegalPassword, name, email))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
