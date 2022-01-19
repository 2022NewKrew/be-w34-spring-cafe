package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.AlreadyExistUserException;
import com.kakao.cafe.exception.IncorrectLoginPasswordException;
import com.kakao.cafe.exception.LoginUserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({"/schema.sql", "/data.sql"})
@TestPropertySource("classpath:test.properties")
@Transactional
@SpringBootTest
class AuthControllerIntegrationTest {

    @Autowired
    private AuthController authController;

    private MockMvc mock;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        mock = MockMvcBuilders.standaloneSetup(authController).build();

        UserDto.UserSessionDto userSessionDto = new UserDto.UserSessionDto("lucas", "test");

        session = new MockHttpSession();
        session.setAttribute("sessionedUser", userSessionDto);
    }

    @DisplayName("login 테스트 - 올바른 Login 정보가 주어지면, sessionAttribute에 sessionedUser 존재")
    @Test
    void login_LoginDto_sessionedUserExist() throws Exception {
        // given
        String userId = "lucas";
        String password = "123";

        session.removeAttribute("sessionedUser");

        // when // then
        mock.perform(post("/auth/login")
                        .param("userId", userId)
                        .param("password", password)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertNotNull(session.getAttribute("sessionedUser"));
    }

    @DisplayName("login 테스트 - userId 가 없는 경우, Throw LoginUserNotFoundException")
    @Test
    void login_IncorrectUserId_ThrowLoginUserNotFoundException() throws Exception {
        // given
        String userId = "incorrectUserId";
        String password = "123";

        session.removeAttribute("sessionedUser");

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/auth/login")
                .param("userId", userId)
                .param("password", password)
                .session(session)))
                .hasCause(new LoginUserNotFoundException(userId));
    }

    @DisplayName("login 테스트 - password가 틀린경우, Throw LoginUserNotFoundException")
    @Test
    void login_IncorrectPassword_ThrowLoginUserNotFoundException() throws Exception {
        // given
        String userId = "lucas";
        String password = "incorrectPassword";

        session.removeAttribute("sessionedUser");

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/auth/login")
                .param("userId", userId)
                .param("password", password)
                .session(session)))
                .hasCause(new IncorrectLoginPasswordException());
    }

    @DisplayName("loginForm 테스트 - 호출 시 HttpStatus OK")
    @Test
    void loginForm_Nothing_HttpStatusOk() throws Exception {
        // given

        // when // then
        mock.perform(get("/auth/login/form"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("loginFailForm 테스트 - 호출 시 HttpStatus OK")
    @Test
    void loginFailForm_Nothing_HttpStatusOk() throws Exception {
        // given

        // when // then
        mock.perform(get("/auth/login/failform"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("logout 테스트 - session Attribute에 sessionedUser 가 존재하지 않음")
    @Test
    void logout_HttpSession_NotExistSessionedUserInSession() throws Exception {
        // given

        // when // then
        mock.perform(get("/auth/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        Assertions.assertNull(session.getAttribute("sessionedUser"));
    }


    @DisplayName("makeUserHtml 테스트 - 요청시 Http Status 2XX 반환")
    @Test
    void makeUserHtml_Nothing_HttpStatus2XX() throws Exception {
        // given

        // when // then
        mock.perform(get("/auth/signup/form"))
                .andExpect(status().isOk());
    }

    @DisplayName("makeUser 테스트 - 중복되지 않는 유저 생성시 Http Status 3XX 반환")
    @Test
    void makeUser_NotDuplicateUserId_HttpStatus3XX() throws Exception {
        // given
        String userId = "correctUserId";

        // when // then
        mock.perform(post("/auth/signup").param("userId", userId)
                        .param("password", "testPassword")
                        .param("email", "test@daum.net")
                        .param("name", "testName"))
                .andExpect(status().is3xxRedirection()).andDo(print());
    }

    @DisplayName("makeUser 테스트 - 중복되는 유저 생성시 AlreadyExistUser Throw")
    @Test
    void makeUser_DuplicateUserId_ThrowAlreadyExistUser() {
        // given
        String userId = "lucas";

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/auth/signup")
                .param("userId", userId)
                .param("password", "testPassword")
                .param("email", "test@daum.net")
                .param("name", "testName")))
                .hasCause(new AlreadyExistUserException(userId));
    }
}
