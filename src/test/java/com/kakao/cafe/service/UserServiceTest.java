package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.util.exception.throwable.InvalidPasswordException;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.exception.wrappable.LoginFailException;
import com.kakao.cafe.util.exception.wrappable.UserDuplicateException;
import com.kakao.cafe.util.exception.wrappable.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Transactional
@SpringBootTest
public class UserServiceTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }


    @BeforeEach
    void setUp() {
        userService.deleteAll();
    }

    @Test
    void insertDuplicateTest() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("asdfg")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        User dupUser = new User.Builder()
                .email("email@maile.com")
                .password("12345")
                .id("yunyul")
                .name("중복렬").build();
        assertThatThrownBy(() -> userService.insert(dupUser)).isInstanceOf(UserDuplicateException.class);
    }

    @Test
    void findByIdFailTest() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("asdfg")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        assertThatThrownBy(() -> userService.findById("eden")).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    void findByIdTest() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("asdfg")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        assertThat(userService.findById("yunyul")).isNotNull();
    }

    @Test
    void updatePasswordFailTest() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("asdfg")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        User newInfo = new User.Builder()
                .email("email@email.com")
                .password("asdfg")
                .id("yunyul")
                .name("윤렬2").build();
        String oldPassword = "wrong_password";
        assertThatThrownBy(() -> userService.update(newInfo, oldPassword)).isInstanceOf(InvalidPasswordException.class);
    }


    @Test
    void updateTest() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("right_password")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        User newInfo = new User.Builder()
                .email("email@email.com")
                .password("change_password")
                .id("yunyul")
                .name("윤렬").build();
        String oldPassword = "right_password";
        userService.update(newInfo, oldPassword);
        User user2 = userService.findById(user.getId());
        assertThat(
                (user.getId().equals(user2.getId())) &&
                        (user2.getPassword().equals("change_password"))
        ).isTrue();

    }

    @Test
    void findAll() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("right_password")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        User user2 = new User.Builder()
                .email("email@email.com")
                .password("right_password")
                .id("yunyul2")
                .name("윤렬").build();
        userService.insert(user2);
        assertThat(userService.findAll().size()).isEqualTo(2);
    }

    @Test
    void notExistsUserLoginFail() {
        assertThatThrownBy(() -> userService.login("not_exist_id", "not_exist_password"))
                .isInstanceOf(LoginFailException.class);
    }


    @Test
    void wrongPasswordLoginFail() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("right_password")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        assertThatThrownBy(() -> userService.login("yunyul", "wrong_password"))
                .isInstanceOf(LoginFailException.class);

    }

    @Test
    void loginSuccess() {
        User user = new User.Builder()
                .email("email@email.com")
                .password("right_password")
                .id("yunyul")
                .name("윤렬").build();
        userService.insert(user);
        assertThat(userService.login("yunyul", "right_password")).isEqualTo("yunyul");
    }

    @Test
    void canUpdateFailByDifferentStrings() {
        assertThatThrownBy(() -> userService.canUpdate("diff1", "diff2")).isInstanceOf(UnauthorizedActionException.class);
    }


}
