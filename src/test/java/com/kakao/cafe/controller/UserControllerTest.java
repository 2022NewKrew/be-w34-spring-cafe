package com.kakao.cafe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc.perform(get("/users/deleteAll"));
    }

    @Test
    public void user_create_test() throws Exception {
        this.mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul")
                        .param("password", "1q2w3e4r")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void user_dup_error_test() throws Exception {
        this.mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));
        this.mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul")
                        .param("password", "1q2w3e4r")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void user_update_fail_test() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "1q2w3e4r")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul")
                .param("password", "1q2w3e4r"));


        mockMvc.perform(post("/users/yunyul")
                        .param("userId", "yunyul")
                        .param("oldPassword", "wrong_password")
                        .param("password", "1q2w3e5t")
                        .param("name", "new윤렬")
                        .param("email", "eden.yoon@kakao.com"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    void user_update_test() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();

        mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul")
                        .param("password", "1q2w3e4r")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul")
                .param("password", "1q2w3e4r"));

        mockMvc.perform(post("/users/yunyul")
                        .param("userId", "yunyul")
                        .param("oldPassword", "1q2w3e4r")
                        .param("password", "1q2w3e5t")
                        .param("name", "new윤렬")
                        .param("email", "eden.yoon@kakao.com"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/users/yunyul"))
                .andExpect(model().attribute("user",
                        allOf(
                                hasProperty("password", is("1q2w3e5t")),
                                hasProperty("name", is("new윤렬")),
                                hasProperty("email", is("eden.yoon@kakao.com"))
                        )
                ));
    }

    @Test
    void login_page_access() throws Exception {
        this.mockMvc.perform(get("/users/login"))
                .andExpect(view().name("user/login"));
    }

    @Test
    void login_not_exists_user_fail() throws Exception {
        this.mockMvc.perform(post("/users/login")
                        .param("id", "not_exists")
                        .param("password", "not_exists"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void login_wrong_password_fail() throws Exception {
        this.mockMvc.perform(post("/user/create")
                .param("userId", "yunyul")
                .param("password", "right_password")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        this.mockMvc.perform(post("/users/login")
                        .param("id", "yunyul")
                        .param("password", "wrong_password"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_success() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();

        mockMvc.perform(post("/user/create")
                        .param("userId", "yunyul3")
                        .param("password", "right_password")
                        .param("name", "윤렬")
                        .param("email", "eden.yoon@kakaocorp.com"))
                .andDo(print());

        mockMvc.perform(post("/users/login")
                        .param("id", "yunyul3")
                        .param("password", "right_password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute("sessionId", "yunyul3"))
                .andDo(print());

        mockMvc.perform(get("/posts"))
                .andExpect(request().sessionAttribute("sessionId", "yunyul3"))
                .andExpect(content().string(containsString("id=\"logout_button\"")))
                .andExpect(content().string(containsString("id=\"user_modify_button\"")))
                .andExpect(content().string(not(containsString("id=\"login_button\""))))
                .andExpect(content().string(not(containsString("id=\"sign_up_button\""))))
                .andDo(print());
    }

    @Test
    void main_without_login() throws Exception {
        this.mockMvc.perform(get("/posts"))
                .andExpect(content().string(not(containsString("id=\"logout_button\""))))
                .andExpect(content().string(not(containsString("id=\"user_modify_button\""))))
                .andExpect(content().string(containsString("id=\"login_button\"")))
                .andExpect(content().string(containsString("id=\"sign_up_button\"")));
    }

    @Test
    void user_update_other_fail() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul3")
                .param("password", "right_password")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul4")
                .param("password", "right_password")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul3")
                .param("password", "right_password"));

        this.mockMvc.perform(get("/users/form/yunyul"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void user_logout_test() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
                .apply(sharedHttpSession()) // use this session across requests
                .build();

        mockMvc.perform(post("/user/create")
                .param("userId", "yunyul3")
                .param("password", "right_password")
                .param("name", "윤렬")
                .param("email", "eden.yoon@kakaocorp.com"));

        mockMvc.perform(post("/users/login")
                .param("id", "yunyul3")
                .param("password", "right_password"));

        mockMvc.perform(get("/logout"));

        mockMvc.perform(get("/posts"))
                .andExpect(request().sessionAttribute("sessionId", is(nullValue())));

    }

}
