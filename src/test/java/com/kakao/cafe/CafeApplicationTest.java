package com.kakao.cafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@SpringBootTest
@AutoConfigureMockMvc
public class CafeApplicationTest {

    private static final AtomicInteger userCounter = new AtomicInteger(0);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(sharedHttpSession())
                .build();
    }

    @Test
    public void signUp() throws Exception {
        MockHttpServletRequestBuilder request = createSignUpRequest();
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void userList() throws Exception {
        signUpAndThen(result -> mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test_name"))));
    }

    @Test
    public void profile_me() throws Exception {
        signUpAndThen(result -> mvc.perform(get("/users/me"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test_name"))));
    }

    @Test
    public void profile_me_notLoggedIn() throws Exception {
        mvc.perform(get("/users/me"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void profile_id() throws Exception {
        signUpAndThen(result -> mvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test_name"))));
    }

    @Test
    public void postArticle() throws Exception {
        signUpAndThen(result -> mvc.perform(createArticleRequest())
                .andDo(print())
                .andExpect(status().is3xxRedirection()));
    }

    @Test
    public void postArticle_notLoggedIn() throws Exception {
        mvc.perform(createArticleRequest())
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void articleList() throws Exception {
        mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void articleList_content() throws Exception {
        postArticleAndThen(result -> mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test_title"))));
    }

    private void signUpAndThen(ResultHandler handler) throws Exception {
        mvc.perform(createSignUpRequest()).andDo(handler);
    }

    private void postArticleAndThen(ResultHandler handler) throws Exception {
        signUpAndThen(result -> mvc.perform(createArticleRequest()).andDo(handler));
    }

    private MockHttpServletRequestBuilder createSignUpRequest() {
        return get("/user/create")
                .queryParam("userId", "test_user" + userCounter.getAndIncrement())
                .queryParam("password", "test_pass")
                .queryParam("name", "test_name")
                .queryParam("email", "test@example.com");
    }

    private MockHttpServletRequestBuilder createArticleRequest() {
        return post("/articles")
                .param("writer", "test_author")
                .param("title", "test_title")
                .param("contents", "test_content");
    }
}
