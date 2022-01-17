package com.kakao.cafe.dao.article;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("VolatilityArticleStorage 테스트")
class VolatilityArticleStorageTest {

    private static final int PRECONDITION_NUMBER_OF_ARTICLE_EXIST = 10;
    private ArticleDao articleDao;

    @BeforeEach
    private void before() {
        articleDao = new VolatilityArticleStorage();
        for (int i = 1; i <= PRECONDITION_NUMBER_OF_ARTICLE_EXIST; i++) {
            articleDao.addArticle(
                    new Title("title" + i),
                    new Writer("writer" + i),
                    new Contents("contents" + i)
            );
        }
    }

    @AfterEach
    void after() {
        articleDao = null;
    }

    @DisplayName("설정된 초기값이 있을때 getArticles 메서드를 사용하면 기대하는 결과값을 반환한다.")
    @Test()
    public void getArticles() {
        //give
        int pageNumber = 1;
        int articlesPerPage = 5;

        //when
        List<Article> articles = articleDao.getArticles(pageNumber, articlesPerPage);

        //then
        assertThat(articles.size()).isEqualTo(articlesPerPage);
        assertThat(articles.get(0).getId()).isEqualTo(PRECONDITION_NUMBER_OF_ARTICLE_EXIST);
    }

    @DisplayName("설정된 초기 값이 있을때 새로운 addArticle 메서드를 실행하면 새로운 Article을 추가한다.")
    @Test
    public void addArticle() {
        //give
        Title title = new Title("newArticle");
        Writer writer = new Writer("writer");
        Contents contents = new Contents("contents");

        //when
        articleDao.addArticle(title, writer, contents);
        Article article = articleDao
                .findArticleById(PRECONDITION_NUMBER_OF_ARTICLE_EXIST + 1)
                .orElseGet(null);

        //then
        assertThat(article.getTitle()).isEqualTo(title);
    }

    @DisplayName("설정된 초기 값이 있을때 id로 Article을 찾으면 기대하는 값을 가져온다.")
    @Test
    public void findArticleById() {
        //give
        int id = PRECONDITION_NUMBER_OF_ARTICLE_EXIST;

        //when
        Article article = articleDao.findArticleById(id).orElseGet(null);

        //then
        assertThat(article.getTitle()).isEqualTo(
                new Title("title" + PRECONDITION_NUMBER_OF_ARTICLE_EXIST));
    }

    @DisplayName("설정된 초기 값이 있을때 저장한 총 Article의 수를 반환한다.")
    @Test
    public void getSize() {
        //give
        int expectSize = PRECONDITION_NUMBER_OF_ARTICLE_EXIST;

        //when
        int realSize = articleDao.getSize();

        //then
        assertThat(realSize).isEqualTo(expectSize);
    }
}
