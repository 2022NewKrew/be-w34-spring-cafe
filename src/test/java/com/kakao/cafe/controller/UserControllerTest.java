package com.kakao.cafe.controller;

import com.kakao.cafe.dto.UserLoginDto;
import com.kakao.cafe.dto.UserRegistrationDto;
import com.kakao.cafe.entity.User;
import com.kakao.cafe.service.AuthService;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final Integer id1 = 3;
    private static final String userId1 = "testUserId1";
    private static final String password1 = "testPassword1";
    private static final String email1 = "Email1";
    private static final Integer id2 = 4;
    private static final String userId2 = "testUserId2";
    private static final String password2 = "testPassword2";
    private static final String email2 = "Email2";

    @DisplayName("유저 리스트 반환 -> 정상")
    @Test
    void userList() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }


    @DisplayName("[성공] 유저 생성")
    @ParameterizedTest(name = "{0}, {1}, {2}")
    @CsvSource(value = {"userId1, password1, email1", "userId2, password2, email2", "userId3, password3, email3"})
    void createUser(String userId, String password, String email) throws Exception {

        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("email", email))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }


    @DisplayName("[실패] 유저 생성 NULL")
    @ParameterizedTest(name = "{0}, {1}, {2}")
    @CsvSource(value = {"null, password1, email1", "userId2, null, email2", "userId3, password3, null"}, nullValues = {"null"})
    void createUser_Null(String userId, String password, String email) throws Exception {

        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("email", email))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("[실패] 유저 생성 BLANK")
    @ParameterizedTest(name = "{0}, {1}, {2}")
    @CsvSource(value = {"' ', password1, email1", "userId2, ' ', email2", "userId3, password3, ' '"})
    void createUser_Blank(String userId, String password, String email) throws Exception {

        mockMvc.perform(post("/users")
                        .param("userId", userId)
                        .param("password", password)
                        .param("email", email))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("[성공] 로그인 되있어서 상세보기")
    void user() throws Exception{
        // given
        User auth = new User(1, "chen", "1234", "chen.kim@kakaocorp.com");

        // when & then
        mockMvc.perform(get("/users/1")
                        .sessionAttr("auth", auth))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"));
    }

    @Test
    @DisplayName("[실패] 로그인 안되있어서 상세보기 실패")
    void user_Fail() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }
}