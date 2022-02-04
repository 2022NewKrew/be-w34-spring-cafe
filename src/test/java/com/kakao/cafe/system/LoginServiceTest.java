package com.kakao.cafe.system;

import com.kakao.cafe.exception.NoSuchUserException;
import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserDto;
import com.kakao.cafe.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by melodist
 * Date: 2022-01-17 017
 * Time: 오후 7:23
 */
@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void login() {
        String userId = "javajigi";
        String name = "자바지기";
        String password = "test";
        String email = "javajigi@test.com";

        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setName(name);
        userDto.setPassword(password);
        userDto.setEmail(email);
        userService.addUser(userDto);
    }

    @Test
    @DisplayName("로그인 성공")
    public void loginSuccess() {
        // given
        String loginId = "javajigi";
        String password = "test";

        // when
        User loginUser = loginService.login(loginId, password);

        // then
        assertThat(loginUser.getUserId()).isEqualTo(loginId);
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 오류")
    public void loginPasswordFail() {
        // given
        String loginId = "javajigi";
        String password = "test1";

        // when
        User loginUser = loginService.login(loginId, password);

        // then
        assertThat(loginUser).isNull();
    }

    @Test
    @DisplayName("로그인 실패 - ID 없음")
    public void loginIdFail() {
        // given
        String loginId = "garujigi";
        String password = "test";

        // when, then
        assertThatThrownBy(() -> loginService.login(loginId, password))
                .isInstanceOf(NoSuchUserException.class);
    }

    @Test
    @DisplayName("유저 생성 후 로그인")
    public void test() {
        // given
        String userId = "melodist";
        String password = "test";
        String name = "test";
        String email = "test@email.com";

        UserDto userDto = new UserDto();

        userDto.setUserId(userId);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setEmail(email);

        userService.addUser(userDto);

        // when
        User loggedInUser = loginService.login(userId, password);

        // then
        assertThat(loggedInUser.getUserId()).isEqualTo(userId);
    }
}
