package com.kakao.cafe.controller.article.advice;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.kakao.cafe.controller.UnexpectedException;
import com.kakao.cafe.controller.advice.GlobalControllerAdvice;
import com.kakao.cafe.controller.article.ArticleController;
import com.kakao.cafe.exception.IllegalPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("ArticleControllerAdvice 테스트")
class ArticleControllerAdviceTest {

    private ArticleController articleController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        articleController = mock(ArticleController.class);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new GlobalControllerAdvice())
                .build();
    }

    @DisplayName("IllegalArgumentException 발생 시 ArticleControllerAdvice에 의해서 예외 처리")
    @Test
    void handleIllegalArgumentException() throws Exception {
        doThrow(new IllegalArgumentException("Exception")).when(
                articleController).postArticle(any());

        mockMvc.perform(post("/articles"))
                .andExpect(redirectedUrl("/questions/form"))
                .andExpect(flash().attributeExists("errorExist"))
                .andExpect(flash().attributeExists("errorMsg"))
                .andReturn();
    }

    @DisplayName("IllegalPermissionException 발생 시 UserControllerAdvice에 의해서 예외 처리")
    @Test
    void handlePermissionException() throws Exception {
        doThrow(new IllegalPermissionException("Exception"))
                .when(articleController).showArticleUpdateForm(anyInt(), any(), any());
        String paramQuery = "?id=1";
        mockMvc.perform(get("/articles/update" + paramQuery))
                .andExpect(redirectedUrl("/articles" + paramQuery))
                .andExpect(flash().attributeExists("errorExist"))
                .andExpect(flash().attributeExists("errorMsg"))
                .andReturn();
    }

    @DisplayName("예상하지 못한 예외 발생시 초기 화면으로 이동")
    @Test
    void handleUnexpectedException() throws Exception {
        doThrow(new UnexpectedException("unexpected")).when(
                articleController).postArticle(any());

        mockMvc.perform(post("/articles"))
                .andExpect(redirectedUrl("/"));
    }
}
