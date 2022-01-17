package com.kakao.cafe.controller;

import com.kakao.cafe.controller.dto.SessionUser;
import com.kakao.cafe.domain.user.Profile;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.domain.user.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    String userId = "유저ID";
    String password = "비밀번호";
    MockHttpSession mockHttpSession;

    @BeforeEach
    void fixture() {
        User user = new User(userId, password, new Profile("이름", "이메일@gmail.com"));
        mockHttpSession = new MockHttpSession();
        userRepository.save(user);
        mockHttpSession.setAttribute("sessionUser", new SessionUser(new UserResponseDto(user)));
    }

    @DisplayName("모든 유저들 조회 테스트")
    @Test
    void getUsersTest() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("user/list"));
    }

    @DisplayName("단일 유저 프로필 조회 테스트")
    @Test
    void getUserTest() throws Exception {
        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/profile"));
    }

    @DisplayName("유저 프로필 수정 폼 조회 테스트")
    @Test
    void updateUserFormTest() throws Exception {
        mockMvc.perform(get("/users/" + userId + "/update")
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(view().name("user/updateForm"));
    }

    @DisplayName("유저 생성 테스트")
    @Test
    void createUserTest() throws Exception {
        mockMvc.perform(post("/users/")
                    .param("userId", "유저ID2")
                    .param("password", "12345678")
                    .param("name", "유저이름")
                    .param("email", "email@email.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @DisplayName("유저 수정 테스트")
    @Test
    void updateUserTest() throws Exception {
        mockMvc.perform(put("/users/" + userId)
                        .param("userId", userId)
                        .param("confirmPassword", password)
                        .param("newPassword", "1234")
                        .param("name", "바뀐유저이름")
                        .param("email", "바뀐이메일@email.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }
}
