package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.UserRepository;
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
class AuthControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception {
        // Given
        User user = User.builder().uid("uid").password("pwd").name("name").email("email@test.com")
            .build();
        when(userRepository.findUserByUid(any()))
            .thenReturn(Optional.of(user));

        // When
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("uid", "uid");
        requestParams.add("password", "pwd");

        ResultActions actions = mockMvc.perform(post("/auth")
            .params(requestParams));

        // Then
        AuthInfo authInfo = (AuthInfo) actions.andReturn()
            .getRequest()
            .getSession()
            .getAttribute("auth");
        assertThat(authInfo.getUid())
            .isEqualTo("uid");

        actions
            .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void logout() throws Exception {
        // Given

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        ResultActions actions = mockMvc.perform(delete("/auth")
            .sessionAttr("auth", authInfo));

        // Then
        assertThat(actions.andReturn().getRequest().getSession().getAttribute("auth"))
            .isNull();

        actions
            .andExpect(redirectedUrl("/"));
    }
}