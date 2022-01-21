package com.kakao.cafe.adapter.in.presentation.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.application.article.dto.ArticleInfo;
import com.kakao.cafe.application.article.dto.ArticleList;
import com.kakao.cafe.application.article.port.in.GetArticleInfoUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.Article;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ArticlePrintController.class)
class ArticlePrintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetArticleInfoUseCase getArticleInfoUseCase;

    @MockBean
    private DataSource dataSource;

    @DisplayName("게시글 목록 출력 테스트")
    @Test
    void displayArticleList() throws Exception {
        // given
        String url = "/";
        List<ArticleInfo> givenArticleList = new ArrayList<>();
        givenArticleList.add(new ArticleInfo(5, "kakao", "krew", "2022-01-10 20:00"));
        givenArticleList.add(new ArticleInfo(10, "HaChanho", "champ", "2022-01-11 21:00"));
        given(getArticleInfoUseCase.getListOfAllArticles()).willReturn(ArticleList.from(givenArticleList));

        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML))
                                  .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                                  .andReturn();

        List<ArticleInfo> responseUserList = (List<ArticleInfo>) Objects.requireNonNull(result.getModelAndView())
                                                                        .getModelMap().get("articles");

        // then
        for (int i = 0; i < givenArticleList.size(); i++) {
            assertThat(givenArticleList.get(i)).isEqualTo(responseUserList.get(i));
        }
    }

    @DisplayName("게시글 상세보기 테스트")
    @Test
    void displayArticleDetail() throws Exception {
        // given
        int id = 5;
        String userId = "kakao";
        String writer = "champ";
        String title = "HaChanho";
        String contents = "champ@kakao.com";
        String createdAt = "2022-01-10 15:23";
        Article givenArticle = new Article.Builder().userId(userId)
                                                    .writer(writer)
                                                    .title(title)
                                                    .contents(contents)
                                                    .createdAt(createdAt)
                                                    .deleted(false)
                                                    .build();
        UserInfo sessionedUser = new UserInfo(userId, writer, "kakao@kakao.com");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", sessionedUser);
        String url = "/articles/" + id;
        given(getArticleInfoUseCase.getArticleForUpdate(id, sessionedUser.getUserId(), sessionedUser))
            .willReturn(givenArticle);

        //when
        mockMvc.perform(MockMvcRequestBuilders.get(url).session(session).accept(MediaType.TEXT_HTML))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print())
               .andReturn();
    }
}
