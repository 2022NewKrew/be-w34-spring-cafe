package com.kakao.cafe.controller;

import com.kakao.cafe.exception.CafeExceptionHandler;
import com.kakao.cafe.exception.user.DuplicateUserIdException;
import com.kakao.cafe.exception.user.IncorrectPasswordException;
import com.kakao.cafe.exception.user.UserNotFoundException;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(CafeExceptionHandler.class)
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @Test
    @DisplayName("회원가입 성공")
    public void signupTest() throws Exception {
        UserDto user = generateUser();
        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
            .flashAttr("user", user)
        ).andExpectAll(
            status().is3xxRedirection(),
            redirectedUrl("")
        );
    }

    @Test
    @DisplayName("회원가입 실패 - 중복 아이디")
    public void signupDuplicateTest() throws Exception {
        UserDto user = generateUser();
        doThrow(new DuplicateUserIdException()).when(userService).signupUser(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/signup")
            .flashAttr("user", user)
        ).andExpectAll(
            status().isOk(),
            view().name("error"),
            model().attribute("errorStatus", HttpStatus.CONFLICT),
            model().attribute("errorMessage", "이미 존재하는 아이디입니다.")
        );
    }

    @Test
    @DisplayName("회원정보 변경 성공")
    public void updateTest() throws Exception {
        UserDto user = generateUser();

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}/update", user.getUserId())
            .flashAttr("user", user)
            .param("newPassword", "2345")
        ).andExpectAll(
            status().is3xxRedirection(),
            redirectedUrl("")
        );
    }

    @Test
    @DisplayName("회원정보 변경 실패 - 비밀번호 불일치")
    public void updateIncorrectPasswordTest() throws Exception {
        UserDto user = generateUser();
        doThrow(new IncorrectPasswordException()).when(userService).updateUser(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}/update", user.getUserId())
            .flashAttr("user", user)
            .param("newPassword", "2345")
        ).andExpectAll(
            status().isOk(),
            view().name("error"),
            model().attribute("errorStatus", HttpStatus.BAD_REQUEST),
            model().attribute("errorMessage", "잘못된 비밀번호입니다.")
        );
    }

    @Test
    @DisplayName("로그인 성공")
    public void loginTest() throws Exception {
        UserDto user = generateUser();
        doReturn(user).when(userService).loginUser(user.getUserId(), user.getPassword());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
            .param("userId", user.getUserId())
            .param("password", user.getPassword())
        ).andExpectAll(
            status().is3xxRedirection(),
            request().sessionAttribute("sessionedUser", user),
            redirectedUrl("/")
        );
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    public void loginIncorrectPasswordTest() throws Exception {
        UserDto user = generateUser();
        doThrow(new IncorrectPasswordException()).when(userService).loginUser(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
            .param("userId", user.getUserId())
            .param("password", "2345")
        ).andExpectAll(
            status().isOk(),
            view().name("error"),
            model().attribute("errorStatus", HttpStatus.BAD_REQUEST),
            model().attribute("errorMessage", "잘못된 비밀번호입니다.")
        );
    }

    @Test
    @DisplayName("로그인 실패 - 아이디 존재X")
    public void loginUserNotFoundTest() throws Exception {
        UserDto user = generateUser();
        doThrow(new UserNotFoundException()).when(userService).loginUser(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
            .param("userId", "test100")
            .param("password", "2345")
        ).andExpectAll(
            status().isOk(),
            view().name("error"),
            model().attribute("errorStatus", HttpStatus.NOT_FOUND),
            model().attribute("errorMessage", "존재하지 않는 계정입니다.")
        );
    }

    private UserDto generateUser() {
        return new UserDto("test", "1234", "test", "test@kakaocorp.com");
    }
}
