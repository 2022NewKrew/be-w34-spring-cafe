package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
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

@WebMvcTest(UserUpdateController.class)
class UserUpdateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UpdateUserInfoUseCase updateUserInfoUseCase;

    @DisplayName("개인 정보 변경 테스트")
    @Test
    void updateUserInfo() throws Exception {
        String userId = "champ";
        String name = "HaChanho";
        String email = "champ@kakao.com";
        String url = "/users/" + userId + "/form";

        // then
        mockMvc.perform(
                   MockMvcRequestBuilders.post(url)
                                         .param("name", name)
                                         .param("email", email)
                                         .accept(MediaType.TEXT_HTML)
               )
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andDo(MockMvcResultHandlers.print());
    }
}
