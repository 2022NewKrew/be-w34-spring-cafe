package com.kakao.cafe.dao.article;

import com.kakao.cafe.dao.TestConfig;
import com.kakao.cafe.model.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JdbcArticleStorage 테스트")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
class JdbcArticleStorageTest {

    public static final int PRECONDITION_ARTICLE_LENGTH = 10;
    static ArticleDao articleDao;

    @Autowired
    JdbcArticleStorageTest(DataSource dataSource) {
        articleDao = new JdbcArticleStorage(new JdbcTemplate(dataSource));
    }

    @DisplayName("설정된 초기값이 있을때 getArticles 메서드를 사용하면 기대하는 결과값을 반환한다.")
    @Test
    void getArticles() {
        //give
        int page = 1;
        int articlesPerPage = 5;

        //when
        List<Article> articles = articleDao.getArticles(page, articlesPerPage);

        //then
        assertThat(articles.size()).isEqualTo(articlesPerPage);
        assertThat(articles.get(0).getId()).isEqualTo(PRECONDITION_ARTICLE_LENGTH);
    }

    @DisplayName("설정된 초기 값이 있을때 새로운 addArticle 메서드를 실행하면 새로운 Article을 추가한다.")
    @Test
    void addArticle() {
        //give
        String title = "newTitle";
        String writer = "writer";
        String contents = "contents";

        //when
        articleDao.addArticle(title, writer, contents);
        Article article = articleDao
                .findArticleById(PRECONDITION_ARTICLE_LENGTH + 1)
                .orElseGet(null);
        //then
        assertThat(article.getTitle())
                .isEqualTo(title);
    }

    @DisplayName("설정된 초기 값이 있을때 id로 Article을 찾으면 기대하는 값을 가져온다.")
    @Test
    void findArticleById() {
        //give
        int id = PRECONDITION_ARTICLE_LENGTH;
        //when
        Article article = articleDao
                .findArticleById(id)
                .orElseGet(null);

        assertThat(article.getId()).isEqualTo(id);
    }

    @DisplayName("설정된 초기 값이 있을때 저장한 총 Article의 수를 반환한다.")
    @Test
    void getSize() {
        //give
        //when
        int size = articleDao.getSize();
        //then
        assertThat(size).isEqualTo(PRECONDITION_ARTICLE_LENGTH);
    }
}
