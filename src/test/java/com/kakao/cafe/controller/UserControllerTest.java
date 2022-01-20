package com.kakao.cafe.controller;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.dto.user.UserInfoDto;
import com.kakao.cafe.dto.user.UserProfileDto;
import com.kakao.cafe.dto.user.UserSessionDto;
import com.kakao.cafe.exception.ExceptionAdvice;
import com.kakao.cafe.exception.LoginFailedException;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    MockHttpSession mockHttpSession;

    MockMvc mockMvc;

    @BeforeEach
    void init() {
        generateUserDto();
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(ExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void signUpSuccess() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signup")
                        .param("userId","david.dh")
                        .param("password","1234")
                        .param("name","김대환")
                        .param("email","david.dh@kakaocorp.com")
        ).andExpect(
                MockMvcResultMatchers.status().is3xxRedirection()
        ).andExpect(
                MockMvcResultMatchers.redirectedUrl("/")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 가입 입력 오류")
    void signUpInputError() throws Exception {
         mockMvc.perform(
                MockMvcRequestBuilders.post("/user/signup")
                        .param("userId","david.dh")
                        .param("password","1234")
                        .param("name","")
                        .param("email","david.dh@kakaocorp.com")
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./error/alert")
         ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("회원 목록 조회 성공")
    void getUsers() throws Exception {
        List<UserInfoDto> users = new ArrayList<>();
        UserInfoDto userInfoDto = generateUserInfoDto();
        users.add(userInfoDto);
        UserSessionDto sessiondUser = new UserSessionDto();
        sessiondUser.setUserId("david.dh");
        mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("sessionedUser",sessiondUser);
        when(userService.getUsers()).thenReturn(users);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/users")
                        .session(mockHttpSession)
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
        UserDto userDto = generateUserDto();
        UserProfileDto userProfileDto = generateUserProfileDto();
        when(userService.getUserProfile(userDto.getUserId())).thenReturn(userProfileDto);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/user/{userId}",userDto.getUserId())
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.model().attribute("matchedUser",userProfileDto)
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./user/profile")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception {
        UserSessionDto sessiondUser = generateUserSessionDto();
        when(userService.login(any())).thenReturn(sessiondUser);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/login")
                        .param("userId","david.dh")
                        .param("password","1234")
        ).andExpect(
                MockMvcResultMatchers.status().is3xxRedirection()
        ).andExpect(
                MockMvcResultMatchers.request().sessionAttribute("sessionedUser",sessiondUser)
        ).andExpect(
                MockMvcResultMatchers.redirectedUrl("/")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFailed() throws Exception {
        when(userService.login(any())).thenThrow(LoginFailedException.class);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/login")
                        .param("userId","david.dh")
                        .param("password","123")
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./user/login_failed")
        ).andDo(MockMvcResultHandlers.print());
    }

    UserDto generateUserDto() {
        UserDto userDto = new UserDto();
        userDto.setName("김대환");
        userDto.setUserId("david.dh");
        userDto.setPassword("1234");
        userDto.setEmail("david.dh@kakaocorp.com");
        return userDto;
    }

    UserInfoDto generateUserInfoDto() {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUserId("david.dh");
        userInfoDto.setName("김대환");
        userInfoDto.setEmail("david.dh@kakaocorp.com");
        return userInfoDto;
    }

    UserProfileDto generateUserProfileDto() {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setEmail("david.dh@kakaocorp.com");
        userProfileDto.setName("김대환");
        return userProfileDto;
    }

    UserSessionDto generateUserSessionDto() {
        UserSessionDto sessiondUser = new UserSessionDto();
        sessiondUser.setUserId("david.dh");
        return sessiondUser;
    }
}
