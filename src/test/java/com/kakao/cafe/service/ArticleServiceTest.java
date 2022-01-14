package com.kakao.cafe.service;

import com.kakao.cafe.dao.article.ArticleDao;
import com.kakao.cafe.dao.article.VolatilityArticleStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("ArticleService 테스트")
class ArticleServiceTest {

    static ArticleDao articleDao;

    static ArticleService articleService;

    @BeforeAll
    static void before() {
        articleDao = mock(VolatilityArticleStorage.class);
        articleService = new ArticleService(articleDao);
    }

    @DisplayName("페이지와 한 화면에 출력할 수 있는 Article의 최대값을 입력 받았을 때 Page 리스트를 만들어서 반환한다.")
    @Test
    void getPages() {
        //give
        int articlesPerPage = 5;
        int articleSize = 20;
        int expectResult = (int) Math.ceil((double) articleSize / articlesPerPage);
        when(articleDao.getSize()).thenReturn(articleSize);
        //when
        List<Integer> pages = articleService.getPages(articlesPerPage);
        //then
        assertThat(pages.size()).isEqualTo(expectResult);
    }
}
