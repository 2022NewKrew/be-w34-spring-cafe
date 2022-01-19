package com.kakao.cafe.service.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kakao.cafe.dao.user.UserDao;
import com.kakao.cafe.dao.user.VolatilityUserStorage;
import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.user.dto.UserLoginDto;
import com.kakao.cafe.service.user.dto.UserRegisterDto;
import com.kakao.cafe.service.user.dto.UserUpdateDto;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        UserUpdateDto userUpdateDto = new UserUpdateDto(userId, password, name, email);

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.of(
                        new User(new UserId(userId), new Password(password), new Name(name),
                                new Email(email))));

        //when
        //then
        assertThatCode(() -> userService.updateUser(userUpdateDto))
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
        UserUpdateDto userUpdateDto = new UserUpdateDto(userId, illegalPassword, name, email);

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.of(
                        new User(new UserId(userId), new Password(password), new Name(name),
                                new Email(email))));

        //when
        //then
        assertThatThrownBy(() -> userService.updateUser(userUpdateDto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 UserId와 일치하는 user가 존재하고 입력받은 비밀번호도 일치할 때 예외를 던지지 않는다.")
    @Test
    void legalLogin() {
        //give
        String userId = "userId";
        String password = "password";
        UserLoginDto userLoginDto = new UserLoginDto(userId, password);

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(
                        Optional.of(
                                new User(
                                        new UserId(userId),
                                        new Password(password),
                                        new Name("name"),
                                        new Email("email"))));
        //when
        //then
        assertThatCode(() -> userService.login(userLoginDto)).doesNotThrowAnyException();
    }

    @DisplayName("입력받은 userId와 일치하는 user가 없을 때 illegalArgumentException을 던진다.")
    @Test
    void illegalLoginWithIllegalUserId() {
        //give
        String userId = "userId";
        String password = "password";
        UserLoginDto userLoginDto = new UserLoginDto(userId, password);

        when(userDao.findUserById(new UserId(userId))).thenReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> userService.login(userLoginDto))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("찾는 사용자가");
    }

    @DisplayName("입력받은 비밀번호가 일치하지 않을 때 illegalArgumentException을 던진다.")
    @Test
    void illegalLoginWithIllegalPassword() {
        //give
        String userId = "userId";
        String password = "notSame";
        UserLoginDto userLoginDto = new UserLoginDto(userId, password);

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(
                        Optional.of(
                                new User(
                                        new UserId(userId),
                                        new Password("password"),
                                        new Name("name"),
                                        new Email("email"))));
        //when
        //then
        assertThatThrownBy(() -> userService.login(userLoginDto)).isInstanceOf(
                IllegalArgumentException.class).hasMessageContaining("비밀번호가");
    }

    @DisplayName("동일한 UserId를 갖는 User가 이미 존재하는 경우 IllegalArgumentException을 던진다.")
    @Test
    public void illegalCreateUserTest() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        UserRegisterDto userRegisterDto = new UserRegisterDto(userId, password, name, email);

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(
                        Optional.of(
                                new User(
                                        new UserId(userId),
                                        new Password(password),
                                        new Name(name),
                                        new Email(email))));

        assertThatThrownBy(() -> userService.createUser(userRegisterDto)).isInstanceOf(
                IllegalArgumentException.class);
    }

    @DisplayName("동일한 UserId를 갖는 User가 존재하지 않는 경우 예외를 던지지 않는다.")
    @Test
    public void legalCreateUserTest() {
        //give
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        UserRegisterDto userRegisterDto = new UserRegisterDto(userId, password, name, email);

        when(userDao.findUserById(new UserId(userId)))
                .thenReturn(Optional.empty());

        assertThatCode(() -> userService.createUser(userRegisterDto)).doesNotThrowAnyException();
    }
}
