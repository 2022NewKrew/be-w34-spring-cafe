package com.kakao.cafe.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.cafe.exception.CustomException;
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
import org.springframework.validation.BindException;

@SpringBootTest
@Transactional
public class PostControllerIntegrationTest {

    @Autowired
    private PostController postController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @DisplayName("모든 게시글 리스트 조회 default 게시글 1개")
    @Test
    void getUserList() throws Exception {
        mvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("postList", IsCollectionWithSize.hasSize(1)));
    }

    @DisplayName("올바른 조건으로 게시글 작성")
    @Test
    void write_CorrectInfo() throws Exception {
        MockHttpServletRequestBuilder request = post("/posts")
                .param("writerUserId", "test")
                .param("title", "게시글 테스트")
                .param("content", "게시글 테스트 입니다.");

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName("존재하지 않는 작성자로 게시글 작성")
    @Test
    void write_NotExistWriterUserID() throws Exception {
        MockHttpServletRequestBuilder request = post("/posts")
                .param("writerUserId", "test123")
                .param("title", "게시글 테스트")
                .param("content", "게시글 테스트 입니다.");

        assertThatThrownBy(() -> mvc.perform(request))
                .hasCause(new CustomException(ErrorCode.NO_USER_MATCHED_INPUT));
    }

    @DisplayName("제목이 빈 칸인 게시글 작성")
    @Test
    void write_BlankTitle() throws Exception {
        MockHttpServletRequestBuilder request = post("/posts")
                .param("writerUserId", "test123")
                .param("title", "")
                .param("content", "게시글 테스트 입니다.");

        mvc.perform(request)
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException));
    }

    @DisplayName("존재하는 id로 게시글 상세 조회")
    @Test
    void getPostById_ExistId() throws Exception {
        UUID id = new UUID(0, 1);

        mvc.perform(get("/posts/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"));
    }

    @DisplayName("존재하지 않는 id로 게시글 상세 조회")
    @Test
    void getPostById_NotExistId() throws Exception {
        UUID id = new UUID(0, 2);

        assertThatThrownBy(() -> mvc.perform(get("/posts/{id}", id)))
                .hasCause(new CustomException(ErrorCode.POST_NOT_FOUND));
    }
}
