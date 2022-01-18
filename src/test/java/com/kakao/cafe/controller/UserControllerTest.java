package com.kakao.cafe.controller;

import com.kakao.cafe.exception.CafeExceptionHandler;
import com.kakao.cafe.exception.user.DuplicateUserIdException;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
            MockMvcResultMatchers.status().is3xxRedirection(),
            MockMvcResultMatchers.redirectedUrl("")
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
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.view().name("error"),
            MockMvcResultMatchers.model().attribute("errorStatus", HttpStatus.CONFLICT),
            MockMvcResultMatchers.model().attribute("errorMessage", "이미 존재하는 아이디입니다.")
        );
    }

    private UserDto generateUser() {
        return new UserDto("test", "1234", "test", "test@kakaocorp.com");
    }
}
