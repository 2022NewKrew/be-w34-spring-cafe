package com.kakao.cafe.service.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kakao.cafe.controller.Constant;
import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.dao.article.JdbcArticleStorage;
import com.kakao.cafe.dao.reply.ReplyDao;
import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.reply.Comment;
import com.kakao.cafe.model.reply.Reply;
import com.kakao.cafe.model.user.UserId;
import java.util.List;
import java.util.Optional;
import javax.naming.NoPermissionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ArticleService 테스트")
class ArticleServiceTest {

    private ArticleDao articleDao;
    private ArticleService articleService;

    @BeforeEach
    private void before() {
        articleDao = mock(JdbcArticleStorage.class);
        articleService = new ArticleService(articleDao);
    }

    @DisplayName("페이지와 한 화면에 출력할 수 있는 Article의 최대값을 입력 받았을 때 Page 리스트를 만들어서 반환한다.")
    @Test
    public void getPages() {
        //give
        int articlesPerPage = Constant.MAX_ARTICLES;
        int pageLimit = 5;
        int page = 10;
        int articleSize = 100;
        when(articleDao.getSize()).thenReturn(articleSize);
        //when
        List<Integer> pages = articleService.getPages(articlesPerPage, pageLimit, page);
        //then
        assertThat(pages.size()).isEqualTo(pageLimit);
        assertThat(pages.get(pageLimit - 1)).isEqualTo(10);
    }

    @DisplayName("마지막 페이지 넘버를 가져온다.")
    @Test
    void getLastPageNumber() {
        //give
        int numberOfArticle = 100;
        int articlePerPage = 5;
        when(articleDao.getSize())
                .thenReturn(numberOfArticle);

        //when
        int lastPageNumber = articleService.getLastPageNumber(articlePerPage);

        //then
        assertThat(lastPageNumber).isEqualTo(numberOfArticle / articlePerPage);

    }

    @DisplayName("해당 아이디를 갖는 게시물이 존재할 때 예외를 던지지 않는다.")
    @Test
    void deleteArticle() throws NoPermissionException {
        //give
        int articleId = 1;
        String userId = "userId";
        when(articleDao.findArticleById(articleId)).thenReturn(
                Optional.of(new Article(articleId, new Title("title"), new UserId(userId), new Contents("contents"))));

        //when
        //then
        assertThatCode(()->articleService.deleteArticle(articleId, userId)).doesNotThrowAnyException();
    }
}
