package com.kakao.cafe.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

import com.kakao.cafe.exception.ErrorCode;
import java.util.UUID;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
public class PostControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(sharedHttpSession())
                .build();
    }

    @DisplayName("모든 게시글 리스트 조회 default 게시글 1개")
    @Test
    void getUserList() throws Exception {
        mvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts/list"))
                .andExpect(model().attribute("postList", IsCollectionWithSize.hasSize(1)))
                .andExpect(content().string(containsString("게시글 제목입니다")));
    }

    @DisplayName("올바른 조건으로 게시글 작성")
    @Test
    void write_CorrectInfo() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        MockHttpServletRequestBuilder request = post("/posts")
                .param("title", "안녕하세요")
                .param("content", "게시글 내용");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("제목이 빈 칸인 게시글 작성")
    @Test
    void write_BlankTitle() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        MockHttpServletRequestBuilder request = post("/posts")
                .param("title", "")
                .param("content", "게시글 내용");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/error"))
                .andExpect(content().string(containsString("제목은 빈 칸일 수 없습니다.")))
                .andExpect(content().string(containsString("제목은 1-100자 이어야 합니다.")));
    }

    @DisplayName("로그아웃 상태에서 회원 프로필 조회")
    @Test
    void getPostById_Logout() throws Exception {
        UUID id = new UUID(0, 1);

        mvc.perform(get("/posts/{id}", id))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));
    }

    @DisplayName("존재하는 id로 게시글 상세 조회")
    @Test
    void getPostById_ExistId() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        mvc.perform(get("/posts/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts/detail"))
                .andExpect(model().attributeExists("post"))
                .andExpect(content().string(containsString("안녕하세요. 게시글 내용입니다.")));
    }

    @DisplayName("존재하지 않는 id로 게시글 상세 조회")
    @Test
    void getPostById_NotExistId() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 2);

        mvc.perform(get("/posts/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.POST_NOT_FOUND.getMessage()));
    }

    @DisplayName("작성자가 게시글 수정 폼 조회")
    @Test
    void getPostUpdateForm_Writer() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        mvc.perform(get("/posts/{id}/update", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts/update-form"))
                .andExpect(model().attributeExists("post"))
                .andExpect(content().string(containsString("수정하기")));
    }

    @DisplayName("작성자가 게시글 수정")
    @Test
    void updatePost_Writer() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        MockHttpServletRequestBuilder request = put("/posts/{id}", id)
                .param("title", "수정된 제목")
                .param("content", "수정된 내용");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/" + id));
    }

    @DisplayName("작성자가 아닌 회원이 게시글 수정")
    @Test
    void updatePost_NotWriter() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test2")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        MockHttpServletRequestBuilder request = put("/posts/{id}", id)
                .param("title", "수정된 제목")
                .param("content", "수정된 내용");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.FORBIDDEN_MODIFY_POST.getMessage()));
    }

    @DisplayName("작성자가 게시글 삭제")
    @Test
    void deletePost_Writer() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        mvc.perform(delete("/posts/{id}", id))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("작성자가 아닌 회원이 게시글 수정")
    @Test
    void deletePost_NotWriter() throws Exception {
        MockHttpServletRequestBuilder loginRequest = post("/users/login")
                .param("userId", "test2")
                .param("password", "test123456");

        mvc.perform(loginRequest)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        UUID id = new UUID(0, 1);

        mvc.perform(delete("/posts/{id}", id))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(view().name("errors/error"))
                .andExpect(model().attribute("message", ErrorCode.FORBIDDEN_MODIFY_POST.getMessage()));
    }
}
