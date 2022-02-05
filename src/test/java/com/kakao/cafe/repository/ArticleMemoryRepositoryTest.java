package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Contents;
import com.kakao.cafe.article.domain.Title;
import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleMemoryRepositoryTest {

    private Article article1;
    private Article article2;
    private ArticleRepository articleRepository;

    @Test
    @BeforeAll
    void setUp() {
        article1 = new Article(new UserId("test123"), new Date(), new Title("제목 입니다."), new Contents("내용 입니다."));
        article2 = new Article(new UserId("test123"), new Date(), new Title("제목2"), new Contents("내용2."));
    }

    @Test
    @BeforeEach
    void setUpRepository() {
        articleRepository = new ArticleMemoryRepository();
    }

    @Test
    @DisplayName("ArticleRepository는 Null을 저장할 수 없다.")
    void saveNullArticle() {
        assertThatThrownBy(() -> articleRepository.save(null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    @DisplayName("저장 하지 않은 ArticleId로 불러올 수 없다.")
    void findArticleById() {
        Article saveArticle = articleRepository.save(article1);
        Optional<Article> findArticle = articleRepository.findByArticleId(saveArticle.getArticleId() + 1);

        assertThat(findArticle.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("저장한 Article을 불러올 수 있다.")
    void save() {
        Article saveArticle = articleRepository.save(article1);
        Optional<Article> findArticle = articleRepository.findByArticleId(saveArticle.getArticleId());

        assertThat(findArticle.isPresent()).isEqualTo(true);
        assertThat(findArticle.get().getArticleId()).isEqualTo(saveArticle.getArticleId());
    }

    @Test
    @DisplayName("저장한 Article 전체를 불러올 수 있다.")
    void findAll() {
        List<Article> saveArticles = new ArrayList<>();
        saveArticles.add(articleRepository.save(article1));
        saveArticles.add(articleRepository.save(article2));

        List<Article> findArticles = articleRepository.findAll();
        assertThat(findArticles.size()).isEqualTo(saveArticles.size());
        for (int i = 0; i < findArticles.size(); i++) {
            assertThat(findArticles.get(i).getArticleId()).isEqualTo(saveArticles.get(i).getArticleId());
        }
    }
}
