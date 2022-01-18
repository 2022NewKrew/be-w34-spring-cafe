package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.port.in.LoginUserUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserLoginController.class)
class UserLoginControllerTest {

    @MockBean
    LoginUserUseCase loginUserUseCase;
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("로그인 테스트")
    @Test
    void loginUserTest() throws Exception {
        String userId = "champ";
        String password = "test";
        String url = "/login";

        // then
        mockMvc.perform(
                   MockMvcRequestBuilders.post(url)
                                         .param("userId", userId)
                                         .param("password", password)
                                         .accept(MediaType.TEXT_HTML)
               )
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andDo(MockMvcResultHandlers.print());
    }
}
