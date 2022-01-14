package com.kakao.cafe.controller;

import com.kakao.cafe.exception.AlreadyExistUserException;
import com.kakao.cafe.exception.UserNotFoundException;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    private MockMvc mock;

    @BeforeEach
    public void setUp() {
        mock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @DisplayName("findUsers 테스트 - Users 의 크기가 2")
    @Test
    void findUsers_Nothing_UsersSize2() throws Exception {
        // given

        // when // then
        mock.perform(get("/users")).andExpect(status().isOk()).andExpect(model().attribute("users", IsCollectionWithSize.hasSize(2))).andDo(print());

    }

    @DisplayName("findUser 테스트 - 올바른 userId가 주어질때, attribute 에 user 반환")
    @Test
    void findUser_CorrectUserId_ExistsAttributeUser() throws Exception {
        // given
        String userId = "lucas";

        // when // then
        mock.perform(get("/users/{userId}", userId)).andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());
    }

    @DisplayName("findUser 테스트 - 잘못된 userId가 주어질때, UserNotFoundException Throw")
    @Test
    void findUser_IncorrectUserId_ThrowUserNotFoundException() {
        // given
        String userId = "incorrectUserId";

        // when // then
        assertThatThrownBy(() -> mock.perform(get("/users/{userId}", userId))).hasCause(new UserNotFoundException("Not Found User (user id: " + userId + ")"));
    }

    @DisplayName("makeUserHtml 테스트 - 요청시 Http Status 2XX 반환")
    @Test
    void makeUserHtml_Nothing_HttpStatus2XX() throws Exception {
        // given

        // when // then
        mock.perform(get("/users")).andExpect(status().isOk());
    }

    @DisplayName("makeUser 테스트 - 중복되지 않는 유저 생성시 Http Status 3XX 반환")
    @Test
    void makeUser_NotDuplicateUserId_HttpStatus3XX() throws Exception {
        // given
        String userId = "correctUserId";

        // when // then
        mock.perform(post("/users").param("userId", userId).param("password", "testPassword").param("email", "test@daum.net").param("name", "testName")).andExpect(status().is3xxRedirection()).andDo(print());
    }

    @DisplayName("makeUser 테스트 - 중복되는 유저 생성시 AlreadyExistUser Throw")
    @Test
    void makeUser_DuplicateUserId_ThrowAlreadyExistUser() {
        // given
        String userId = "lucas";

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/users").param("userId", userId).param("password", "testPassword").param("email", "test@daum.net").param("name", "testName"))).hasCause(new AlreadyExistUserException("Already Exist User (user id: " + userId + ")"));
    }

    @DisplayName("updateUserHtml 테스트 - userId가 존재할 때, attribute에 user가 존재")
    @Test
    void updateUserHtml_ExistUserId_ExistAttributeUser() throws Exception {
        // given
        String userId = "lucas";

        // when // then
        mock.perform(get("/users/{userId}/form", userId)).andExpect(status().isOk()).andExpect(model().attributeExists("user")).andDo(print());

    }

    @DisplayName("updateUserHtml 테스트 - userId가 존재하지 않을때, attribute에 user가 존재")
    @Test
    void updateUserHtml_NotExistUserId_ExistAttributeUser() throws Exception {
        // given
        String userId = "notExistLucas";

        // when // then
        assertThatThrownBy(() -> mock.perform(get("/users/{userId}/form", userId))).hasCause(new UserNotFoundException("Not Found User (user id: " + userId + ")"));

    }

    @DisplayName("updateUser 테스트 - 유저가 존재하고 비밀번호가 일치할 때, Http Status 3XX")
    @Test
    void updateUser_ExistUserIdAndCorrectPassword_HttpStatus3XX() throws Exception {
        // given
        String userId = "lucas";
        String password = "123";

        // when // then
        mock.perform(post("/users/{userId}", userId).param("originPassword", password).param("changedPassword", "changedPassword").param("email", "test@daum.net").param("name", "testName")).andExpect(status().is3xxRedirection()).andDo(print());
    }

    @DisplayName("updateUser 테스트 - 유저가 존재하지 않고 비밀번호가 일치할 때, UserNotFoundException Throw")
    @Test
    void updateUser_NotExistUserIdAndCorrectPassword_ThrowUserNotFoundException() {
        // given
        String userId = "incorrectLucas";
        String password = "123";

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/users/{userId}", userId).param("originPassword", password).param("changedPassword", "changedPassword").param("email", "test@daum.net").param("name", "testName"))).hasCause(new UserNotFoundException("Not Found User (user id: " + userId + ")"));
    }

    @DisplayName("updateUser 테스트 - 유저가 존재하지 않고 비밀번호가 일치할 때, UserNotFoundException Throw")
    @Test
    void updateUser_ExistUserIdAndIncorrectPassword_ThrowIllegalArgumentException() {
        // given
        String userId = "lucas";
        String password = "incorrectPassword";

        // when // then
        assertThatThrownBy(() -> mock.perform(post("/users/{userId}", userId).param("originPassword", password).param("changedPassword", "changedPassword").param("email", "test@daum.net").param("name", "testName"))).hasCause(new IllegalArgumentException("Password is incorrect"));
    }
}
