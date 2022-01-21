package com.kakao.cafe.controller;

import com.kakao.cafe.advice.LoginUserMethodArgumentResolver;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.UserNotFoundException;
import org.hamcrest.collection.IsCollectionWithSize;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({"/schema.sql", "/data.sql"})
@TestPropertySource("classpath:test.properties")
@Transactional
@SpringBootTest
class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    private MockMvc mock;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        mock = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new LoginUserMethodArgumentResolver())
                .build();

        UserDto.UserSessionDto userSessionDto = new UserDto.UserSessionDto("lucas", "test");

        session = new MockHttpSession();
        session.setAttribute("sessionedUser", userSessionDto);
    }

    @DisplayName("findUsers 테스트 - Users 의 크기가 2")
    @Test
    void findUsers_Nothing_UsersSize2() throws Exception {
        // given

        // when // then
        mock.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("users", IsCollectionWithSize.hasSize(2)))
                .andDo(print());

    }

    @DisplayName("findUser 테스트 - 올바른 userId가 주어질때, attribute 에 user 반환")
    @Test
    void findUser_CorrectUserId_ExistsAttributeUser() throws Exception {
        // given
        String userId = "lucas";

        // when // then
        mock.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andDo(print());
    }

    @DisplayName("findUser 테스트 - 잘못된 userId가 주어질때, UserNotFoundException Throw")
    @Test
    void findUser_IncorrectUserId_ThrowUserNotFoundException() {
        // given
        String userId = "incorrectUserId";

        // when // then
        assertThatThrownBy(() -> mock.perform(get("/users/{userId}", userId)))
                .hasCause(new UserNotFoundException(userId));
    }


    @DisplayName("updateUserHtml 테스트 - userId가 존재할 때, attribute에 user가 존재")
    @Test
    void updateUserHtml_ExistUserId_ExistAttributeUser() throws Exception {
        // given

        // when // then
        mock.perform(get("/users/updateform")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andDo(print());

    }

    @DisplayName("updateUserHtml 테스트 - userId가 존재하지 않을때, throw UserNotFoundException")
    @Test
    void updateUserHtml_NotExistUserId_ExistAttributeUser() throws Exception {
        // given
        UserDto.UserSessionDto incorrectUserSessionDto = new UserDto.UserSessionDto("notExistLucas", "test");

        session.removeAttribute("sessionedUser");
        session.setAttribute("sessionedUser", incorrectUserSessionDto);

        // when // then
        assertThatThrownBy(() -> mock.perform(get("/users/updateform").session(session)))
                .hasCause(new UserNotFoundException(incorrectUserSessionDto.getUserId()));

    }

    @DisplayName("updateUser 테스트 - 유저가 존재하고 비밀번호가 일치할 때, Http Status 3XX")
    @Test
    void updateUser_ExistUserIdAndCorrectPassword_HttpStatus3XX() throws Exception {
        // given
        String password = "123";

        // when // then
        mock.perform(post("/users").session(session)
                        .param("originPassword", password)
                        .param("changedPassword", "changedPassword")
                        .param("email", "test@daum.net")
                        .param("name", "testName"))
                .andExpect(status().is3xxRedirection()).andDo(print());
    }

    @DisplayName("updateUser 테스트 - 유저가 존재하지 않고 비밀번호가 일치할 때, UserNotFoundException Throw")
    @Test
    void updateUser_NotExistUserIdAndCorrectPassword_ThrowUserNotFoundException() {
        // given
        String password = "123";

        UserDto.UserSessionDto incorrectUserSessionDto = new UserDto.UserSessionDto("notExistLucas", "test");

        session.removeAttribute("sessionedUser");
        session.setAttribute("sessionedUser", incorrectUserSessionDto);

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/users").session(session)
                .param("originPassword", password)
                .param("changedPassword", "changedPassword")
                .param("email", "test@daum.net")
                .param("name", "testName")))
                .hasCause(new UserNotFoundException(incorrectUserSessionDto.getUserId()));
    }

    @DisplayName("updateUser 테스트 - 유저가 존재하지 않고 비밀번호가 일치할 때, UserNotFoundException Throw")
    @Test
    void updateUser_ExistUserIdAndIncorrectPassword_ThrowIllegalArgumentException() {
        // given
        String password = "incorrectPassword";

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/users").session(session)
                .param("originPassword", password)
                .param("changedPassword", "changedPassword")
                .param("email", "test@daum.net")
                .param("name", "testName")))
                .hasCause(new IllegalArgumentException("Password is incorrect"));
    }
}
