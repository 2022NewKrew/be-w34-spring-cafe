package com.kakao.cafe.qna.article;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by melodist
 * Date: 2022-01-18 018
 * Time: 오후 7:00
 */
@SpringBootTest
@ActiveProfiles("mysql")
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("게시글 추가")
    public void insertArticle() throws Exception {
        // given
        String writer = "test1";
        String title = "test2";
        String contents = "test3";
        Article article1 = new Article(writer, title, contents);

        // when
        articleService.saveArticle(article1);
        Article article2 = articleService.findAll().get(1);

        // then
        Assertions.assertThat(article2.getWriter()).isEqualTo(writer);
        Assertions.assertThat(article2.getTitle()).isEqualTo(title);
        Assertions.assertThat(article2.getContents()).isEqualTo(contents);
    }
}
