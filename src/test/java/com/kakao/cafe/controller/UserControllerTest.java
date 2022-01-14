package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.exception.InputDataExceptionAdvice;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    MockMvc mockMvc;
    UserDto userDto;

    @BeforeEach
    void init() {
        userDto = new UserDto();
        userDto.setName("김대환");
        userDto.setUserId("david.dh");
        userDto.setPassword("1234");
        userDto.setEmail("david.dh@kakaocorp.com");
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(InputDataExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void signUpSuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .param("userId","david.dh")
                        .param("password","1234")
                        .param("name","김대환")
                        .param("email","david.dh@kakaocorp.com")
        ).andExpect(
                MockMvcResultMatchers.status().is3xxRedirection()
        ).andExpect(
                MockMvcResultMatchers.redirectedUrl("/users")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 가입 입력 오류")
    void signUpInputError() throws Exception {
         mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .param("userId","david.dh")
                        .param("password","1234")
                        .param("name","")
                        .param("email","david.dh@kakaocorp.com")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./error/alert")
         ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 목록 조회 성공")
    void getUsers() throws Exception {
        List<UserDto> users = new ArrayList<>();
        users.add(userDto);
        when(userService.getUsers()).thenReturn(users);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.model().attribute("users",users)
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./user/list")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 프로필 조회 성공")
    void getProfile() throws Exception {
        when(userService.getUser(userDto.getUserId())).thenReturn(userDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/{userId}",userDto.getUserId())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.model().attribute("matchedUser",userDto)
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./user/profile")
        ).andDo(MockMvcResultHandlers.print());
    }

}
