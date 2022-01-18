package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.cafe.dto.UserDTO.Result;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("사용자 회원 가입 테스트")
    void create() throws Exception {
        // Given

        // When
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("uid", "uid");
        requestParams.add("password", "pwd");
        requestParams.add("name", "name");
        requestParams.add("email", "email@test.com");

        ResultActions actions = mockMvc.perform(post("/users")
            .params(requestParams));

        // Then
        actions
            .andExpect(redirectedUrl("/users"));
    }

    @Test
    @DisplayName("사용자 정보 수정 테스트")
    void update() throws Exception {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .build();
        when(userRepository.findUserByUid(any()))
            .thenReturn(Optional.of(user));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("password", "pwd");
        requestParams.add("name", "modified");
        requestParams.add("email", "modified@test.com");

        ResultActions actions = mockMvc.perform(put("/users")
            .params(requestParams)
            .sessionAttr("auth", authInfo));

        // Then
        actions
            .andExpect(redirectedUrl("/users"));
    }

    @Test
    @DisplayName("인증 정보가 없을 때 사용자 정보 수정 테스트")
    void update2() throws Exception {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .build();
        when(userRepository.findUserByUid(any()))
            .thenReturn(Optional.of(user));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("password", "pwd0");
        requestParams.add("name", "modified");
        requestParams.add("email", "modified@test.com");

        ResultActions actions = mockMvc.perform(put("/users")
            .params(requestParams)
            .sessionAttr("auth", authInfo));

        // Then
        actions
            .andExpect(redirectedUrl("/error"));
    }

    @Test
    @DisplayName("사용자 목록 조회 테스트")
    void readAll() throws Exception {
        // Given
        User user1 = User.builder().uid("uid1").password("pwd1").name("name1")
            .email("email1@test.com")
            .build();
        User user2 = User.builder().uid("uid2").password("pwd2").name("name2")
            .email("email2@test.com")
            .build();
        User user3 = User.builder().uid("uid3").password("pwd3").name("name3")
            .email("email3@test.com")
            .build();
        when(userRepository.findAllUsers())
            .thenReturn(List.of(user1, user2, user3));

        // When
        ResultActions actions = mockMvc.perform(get("/users"));

        // Then
        List<Result> results = (List<Result>) actions.andReturn()
            .getModelAndView()
            .getModelMap()
            .getAttribute("users");
        assertThat(results)
            .isNotNull()
            .isInstanceOf(List.class);
        assertThat(results.size())
            .isEqualTo(3);

        actions
            .andExpect(status().isOk());
        assertThat(actions.andReturn().getModelAndView().getViewName())
            .isNotNull()
            .isEqualTo("/user/list");
    }

    @Test
    @DisplayName("특정 사용자 조회 테스트")
    void read() throws Exception {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .build();
        when(userRepository.findUserByUid(any()))
            .thenReturn(Optional.of(user));

        // When
        ResultActions actions = mockMvc.perform(get("/users/uid"));

        // Then
        Result resultDTO = (Result) actions.andReturn()
            .getModelAndView()
            .getModelMap()
            .getAttribute("user");
        assertThat(resultDTO.getUid())
            .isEqualTo("uid");
        assertThat(resultDTO.getName())
            .isEqualTo("name");
        assertThat(resultDTO.getEmail())
            .isEqualTo("email@test.com");

        actions
            .andExpect(status().isOk());
        assertThat(actions.andReturn().getModelAndView().getViewName())
            .isNotNull()
            .isEqualTo("/user/profile");
    }
}