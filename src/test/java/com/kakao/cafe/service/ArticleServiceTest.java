package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    private ArticleServiceTest() {}

    @Test
    @DisplayName("없는 게시글은 조회할 수 없다.")
    void testGetArticleWithUnusedArticleId() {
        // given
        Integer unusedArticleId = 1;

        // when
        Mockito.when(articleRepository.isArticleIdUsed(unusedArticleId)).thenReturn(false);

        // then
        assertThatThrownBy(() -> articleService.getArticleById(unusedArticleId))
            .isExactlyInstanceOf(ResponseStatusException.class);
    }

    @Test
    @DisplayName("게시글 목록을 확인한다.")
    void testReadArticleList() {
        // given

        // when

        // then
        assertThat(articleService.getArticles()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("게시글을 작성한다.")
    void testCreateArticle() {
        // given
        String writerUserId = "test";
        String writerUid = "1";

        // when
        User writer = new User(writerUid, writerUserId, "123456", "test", "test@test.com");

        // then
        assertThat(articleService.createArticle("testTitle", writer, "testContents"))
            .isExactlyInstanceOf(Article.class);
    }
}
