package com.kakao.cafe.service;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.dao.user.VolatilityUserStorage;
import com.kakao.cafe.model.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.of(new User(new UserId(userId), new Password(password), new Name(name), new Email(email))));

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

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.of(new User(new UserId(userId), new Password(password), new Name(name), new Email(email))));

        //when
        //then
        assertThatThrownBy(() -> userService.updateUser(userId, illegalPassword, name, email))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 유저 아이디, 비밀번호를 갖는 유저가 존재할 때 true를 반환한다..")
    @Test
    void legalHasUser() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String targetUserId = "userId";
        String targetPassword = "password";

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.of(new User(new UserId(userId), new Password(password), new Name(name), new Email(email))));
        //when
        boolean isSame = userService.hasUser(targetUserId, targetPassword);

        //then
        assertThat(isSame).isTrue();
    }

    @DisplayName("입력받은 유저 아이디는 일치하고 비밀번호는 일치하지 않는 유저가 존재할 때 false를 반환한다..")
    @Test
    void illegalHasUser() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String targetUserId = "userId";
        String targetPassword = "notSame";

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.of(new User(new UserId(userId), new Password(password), new Name(name), new Email(email))));
        //when

        boolean isSame = userService.hasUser(targetUserId, targetPassword);

        //then
        assertThat(isSame).isFalse();
    }
}
