package com.kakao.cafe.controller.article.advice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.kakao.cafe.controller.article.ArticleSaveController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("ArticleSaveControllerAdvice 테스트")
class ArticleSaveControllerAdviceTest {

    private ArticleSaveController articleSaveController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        articleSaveController = mock(ArticleSaveController.class);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(articleSaveController)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new ArticleSaveControllerAdvice())
                .build();
    }

    @DisplayName("IllegalArgumentException 발생 시 ArticleSaveControllerAdvice에 의해서 예외 처리")
    @Test
    void handleIllegalArgumentException() throws Exception {
        doThrow(new IllegalArgumentException("Exception")).when(
                articleSaveController).postArticle(any());

        mockMvc.perform(post("/articles"))
                .andExpect(view().name("qna/form"))
                .andExpect(model().attributeExists("errorExist"))
                .andExpect(model().attributeExists("errorMsg"))
                .andReturn();
    }
}
