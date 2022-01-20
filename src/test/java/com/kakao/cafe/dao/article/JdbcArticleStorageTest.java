package com.kakao.cafe.dao.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.ArticleFactory;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
        String userSql = "INSERT INTO USER_DATA(USER_ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        for (int i = 1; i <= 10; i++) {
            jdbcTemplate.update(userSql, "userId" + i, "password" + i, "name" + i, "email" + i);
        }

        String sql = "INSERT INTO ARTICLE(TITLE, USER_ID, CONTENTS) VALUES (?, ?, ?)";
        for (int i = 1; i <= 10; i++) {
            jdbcTemplate.update(sql, "title" + i, "userId" + i, "contents" + i);
        }
    }

    @AfterEach
    private void deleteInitData() {
        String preSql = "SET REFERENTIAL_INTEGRITY FALSE";
        String postSql = "SET REFERENTIAL_INTEGRITY TRUE";
        String truncateArticleSql = "TRUNCATE TABLE ARTICLE";
        String truncateUserSql = "TRUNCATE TABLE USER_DATA";
        String initAutoIncrement = "ALTER TABLE ARTICLE ALTER COLUMN ID RESTART WITH 1";
        jdbcTemplate.execute(preSql);
        jdbcTemplate.execute(truncateArticleSql);
        jdbcTemplate.execute(truncateUserSql);
        jdbcTemplate.execute(initAutoIncrement);
        jdbcTemplate.execute(postSql);
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
        ArticleCreateDto articleCreateDto = new ArticleCreateDto("newArticle", "userId1",
                "contents");
        //when

        Article article = articleDao.addArticle(ArticleFactory.getArticle(articleCreateDto));
        //then
        assertThat(article.getTitle().getValue())
                .isEqualTo("newArticle");
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

    @DisplayName("입력받은 id를 가지는 Article을 삭제한다.")
    @Test
    void deleteArticle() {
        //give
        int id = 1;
        //when
        articleDao.deleteArticle(id);
        //then
        assertThatThrownBy(
                () -> articleDao.findArticleById(id).orElseThrow(IllegalArgumentException::new))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력받은 id와 articleUpdateDto를 통해서 기존의 Article을 수정한다.")
    @Test
    void updateArticle() {
        //give
        int id = 1;
        ArticleUpdateDto articleUpdateDto = new ArticleUpdateDto("userId1", "newTitle",
                "newContents");
        Article article = ArticleFactory.getArticle(1, articleUpdateDto);

        //when
        articleDao.updateArticle(article);
        Article updatedArticle = articleDao.findArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("exception"));

        //then
        assertThat(updatedArticle.getTitle()).isEqualTo(new Title("newTitle"));
        assertThat(updatedArticle.getContents()).isEqualTo(new Contents("newContents"));
    }
}
