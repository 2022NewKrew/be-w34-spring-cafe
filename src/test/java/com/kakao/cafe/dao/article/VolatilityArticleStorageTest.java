package com.kakao.cafe.dao.article;

import static org.assertj.core.api.Assertions.as;
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

@DisplayName("VolatilityArticleStorage 테스트")
class VolatilityArticleStorageTest {

    private static final int PRECONDITION_NUMBER_OF_ARTICLE_EXIST = 10;
    private ArticleDao articleDao;

    @BeforeEach
    private void before() {
        articleDao = new VolatilityArticleStorage();
        for (int i = 1; i <= PRECONDITION_NUMBER_OF_ARTICLE_EXIST; i++) {
            articleDao.addArticle(
                    ArticleFactory.getArticle("title" + i, "writer" + i, "contents" + i));
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
        ArticleCreateDto articleCreateDto = new ArticleCreateDto("newArticle", "writer",
                "contents");

        //when
        articleDao.addArticle(ArticleFactory.getArticle(articleCreateDto));
        Article article = articleDao.findArticleById(PRECONDITION_NUMBER_OF_ARTICLE_EXIST + 1)
                .orElseGet(null);

        //then
        assertThat(article.getTitle().getValue()).isEqualTo("newArticle");
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
        ArticleUpdateDto articleUpdateDto = new ArticleUpdateDto("writer1", "newTitle", "newContents");
        Article article = ArticleFactory.getArticle(1, articleUpdateDto);

        //when
        articleDao.updateArticle(article);
        Article updatedArticle = articleDao.findArticleById(id).orElseThrow(()-> new IllegalArgumentException("exception"));

        //then
        assertThat(updatedArticle.getTitle()).isEqualTo(new Title("newTitle"));
        assertThat(updatedArticle.getContents()).isEqualTo(new Contents("newContents"));
    }
}
