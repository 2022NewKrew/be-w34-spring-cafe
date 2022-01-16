package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VolatilityArticleStorage 테스트")
class VolatilityArticleStorageTest {
    private static final int PRECONDITION_NUMBER_OF_ARTICLE_EXIST = 10;

    private static int startId = 0;
    private ArticleDao articleDao;

    @BeforeEach
    private void before() {
        articleDao = new VolatilityArticleStorage();
        for (int i = 1; i <= PRECONDITION_NUMBER_OF_ARTICLE_EXIST; i++) {
            articleDao.addArticle("title" + i, "writer" + i, "contents" + i);
        }
        startId += 10;
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
        assertThat(articles.get(0).getId()).isEqualTo(startId);
    }

    @DisplayName("설정된 초기 값이 있을때 새로운 addArticle 메서드를 실행하면 새로운 Article을 추가한다.")
    @Test
    public void addArticle() {
        //give
        String title = "newArticle";
        String writer = "writer";
        String contents = "contents";

        //when
        articleDao.addArticle(title, writer, contents);
        Article article = articleDao
                .findArticleById(startId + 1)
                .orElseGet(null);

        //then
        assertThat(article.getTitle()).isEqualTo("newArticle");
    }

    @DisplayName("설정된 초기 값이 있을때 id로 Article을 찾으면 기대하는 값을 가져온다.")
    @Test
    public void findArticleById() {
        //give
        int id = startId - 5;

        //when
        Article article = articleDao.findArticleById(id).orElseGet(null);

        //then
        assertThat(article.getTitle()).isEqualTo("title" + (startId - id));
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
