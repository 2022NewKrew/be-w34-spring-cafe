package com.kakao.cafe.user.controller;

import static com.kakao.cafe.fixture.UserFixture.LOGIN_USER;
import static com.kakao.cafe.fixture.UserFixture.USER1;
import static com.kakao.cafe.fixture.UserFixture.USER2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.kakao.cafe.ControllerTest;
import com.kakao.cafe.common.util.SessionUtil;
import com.kakao.cafe.user.dto.Profile;
import com.kakao.cafe.user.service.UserService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@WebMvcTest(UserController.class)
@Import(SessionUtil.class)
class UserControllerTest extends ControllerTest {

    @MockBean
    private UserService userService;

    @DisplayName("회원가입")
    @Test
    void signup() throws Exception {
        mockMvc.perform(
                post("/user")
                    .param("email", USER1.getEmail())
                    .param("password", USER1.getPassword())
                    .param("nickname", USER1.getNickname())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/user"));

    }

    @DisplayName("회원 목록 조회")
    @Test
    void getAllUsers() throws Exception {
        List<Profile> profiles = List.of(Profile.of(USER1), Profile.of(USER2));
        given(userService.getProfiles()).willReturn(profiles);
        loginUser();

        mockMvc.perform(
                get(("/user"))
                    .session(session)
            )
            .andExpect(status().isOk())
            .andExpect(view().name("user/list"))
            .andExpect(model().attribute("users", profiles));

    }

    @DisplayName("회원 상세 조회")
    @Test
    void getUserById() throws Exception {
        Profile expected = Profile.of(USER1);
        given(userService.getProfileById(anyLong())).willReturn(expected);
        loginUser();

        mockMvc.perform(
                get("/user/" + USER1.getId())
                    .session(session)
            )
            .andExpect(status().isOk())
            .andExpect(view().name("user/profile"))
            .andExpect(model().attribute("user", expected));
    }

    @DisplayName("로그인")
    @Test
    void login() throws Exception {
        given(userService.login(any())).willReturn(LOGIN_USER);

        mockMvc.perform(
                post("/user/login")
                    .session(session)
                    .param("email", USER1.getEmail())
                    .param("password", USER1.getPassword())
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        assertThat(session.getAttribute(SessionUtil.SESSION_USER)).isEqualTo(LOGIN_USER);
    }

    @DisplayName("로그아웃")
    @Test
    void logout() throws Exception {
        loginUser();

        mockMvc.perform(
                get("/user/logout")
                    .session(session)
            )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"));

        assertThat(session.isInvalid()).isTrue();
    }
}