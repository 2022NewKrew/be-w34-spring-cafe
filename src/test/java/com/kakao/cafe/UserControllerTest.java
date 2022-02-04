package com.kakao.cafe;

import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("user 정보 표시")
    void userFormTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/form.html")).
                andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("user form post Test")
    void userCreateTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                        .param("email", "justin@kakaocorp.com")
                        .param("userId", "justin123")
                        .param("name", "justin")
                        .param("password", "1234"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/users"));
    }

    @Test
    @DisplayName("user List Test")
    void userListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("user create success & detail 확인")
    void userDetailTest() throws Exception {
        userCreateTest();
        mockMvc.perform(MockMvcRequestBuilders.get("/users/justin123"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
