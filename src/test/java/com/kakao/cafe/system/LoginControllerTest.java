package com.kakao.cafe.system;

import com.kakao.cafe.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by melodist
 * Date: 2022-01-29 029
 * Time: 오후 11:21
 */
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    public void loginIdValidation() throws Exception{
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("password", "test");
        BDDMockito.given(loginService.login("", "test"))
                .willReturn(new User(1, "test", "test", "", ""));

        // when, then
        mockMvc.perform(post("/login")
                        .params(params))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginPasswordValidation() throws Exception{
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("userId", "test");

        // when, then
        mockMvc.perform(post("/login")
                        .params(params))
                .andExpect(status().isBadRequest());
    }


}
