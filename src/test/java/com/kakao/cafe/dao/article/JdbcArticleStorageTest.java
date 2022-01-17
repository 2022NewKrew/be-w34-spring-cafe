package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JdbcArticleStorage 테스트")
@JdbcTest
class JdbcArticleStorageTest {
    private static final int PRECONDITION_ARTICLE_LENGTH = 10;

    private final ArticleDao articleDao;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcArticleStorageTest(JdbcTemplate jdbcTemplate) {
        this.articleDao = new JdbcArticleStorage(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    private void insertInitData() {
        String sql = "INSERT INTO ARTICLE(TITLE, WRITER, CONTENTS) VALUES (?, ?, ?)";
        for (int i = 1; i <= 10; i++) {
            jdbcTemplate.update(sql, "title" + i, "writer" + i, "contents" + i);
        }
    }

    @AfterEach
    private void deleteInitData() {
        String truncateSql = "TRUNCATE TABLE ARTICLE";
        String initAutoIncrement = "ALTER TABLE ARTICLE ALTER COLUMN ID RESTART WITH 1";
        jdbcTemplate.execute(truncateSql);
        jdbcTemplate.execute(initAutoIncrement);
    }

    @DisplayName("설정된 초기값이 있을때 getArticles 메서드를 사용하면 기대하는 결과값을 반환한다.")
    @Test
    public void getArticles() {
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
    public void addArticle() {
        //give
        Title title = new Title("newTitle");
        Writer writer = new Writer("writer");
        Contents contents = new Contents("contents");
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
    public void findArticleById() {
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
    public void getSize() {
        //give
        //when
        int size = articleDao.getSize();
        //then
        assertThat(size).isEqualTo(PRECONDITION_ARTICLE_LENGTH);
    }
}
