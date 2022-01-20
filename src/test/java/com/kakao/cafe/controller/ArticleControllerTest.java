package com.kakao.cafe.controller;

import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.user.UserSessionDto;
import com.kakao.cafe.exception.ExceptionAdvice;
import com.kakao.cafe.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    ArticleController articleController;
    @Mock
    ArticleService articleService;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .setControllerAdvice(ExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("게시글 수정 성공")
    void updateArticle() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/articles/{id}/update",1)
                        .param("title","수정된 제목")
                        .param("contents", "수정된 내용")
        ).andExpect(
                MockMvcResultMatchers.status().is3xxRedirection()
        ).andExpect(
                MockMvcResultMatchers.redirectedUrl("/")
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }

    @Test
    @DisplayName("게시글 수정 권한 없음")
    void getArticleFormFailed() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        UserSessionDto sessionedUser = new UserSessionDto();
        sessionedUser.setName("김대환");
        sessionedUser.setUserId("david.dh");
        mockHttpSession.setAttribute("sessionedUser",sessionedUser);
        ArticleDto article = new ArticleDto(1,"다른 사용자 이름","제목","내용","날짜","다른 사용자 아이디");
        when(articleService.getArticle(anyInt())).thenReturn(article);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/articles/{id}/update",1)
                        .session(mockHttpSession)
        ).andExpect(
                MockMvcResultMatchers.status().isForbidden()
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./error/alert")
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }

    @Test
    @DisplayName("게시글 삭제 성공")
    void deleteArticle() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        UserSessionDto sessionedUser = new UserSessionDto();
        sessionedUser.setName("김대환");
        sessionedUser.setUserId("david.dh");
        mockHttpSession.setAttribute("sessionedUser",sessionedUser);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/articles/{id}/delete",1)
                        .param("userId","david.dh")
                        .session(mockHttpSession)
        ).andExpect(
                MockMvcResultMatchers.status().is3xxRedirection()
        ).andExpect(
                MockMvcResultMatchers.redirectedUrl("/")
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }

    @Test
    @DisplayName("게시글 삭제 실패 : 다른 사용자 게시글은 삭제할 수 없습니다.")
    void deleteArticleFailed() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        UserSessionDto sessionedUser = new UserSessionDto();
        sessionedUser.setName("김대환");
        sessionedUser.setUserId("david.dh");
        mockHttpSession.setAttribute("sessionedUser",sessionedUser);
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/articles/{id}/delete",1)
                        .param("userId","kdh")
                        .session(mockHttpSession)
        ).andExpect(
                MockMvcResultMatchers.status().isForbidden()
        ).andExpect(
                MockMvcResultMatchers.forwardedUrl("./error/alert")
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }
}
