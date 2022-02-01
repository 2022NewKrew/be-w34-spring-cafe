package com.kakao.cafe.qna.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-01-18 018
 * Time: 오후 7:43
 */
class ArticleTest {

    @Test
    @DisplayName("게시글 수정")
    public void updateArticle() throws Exception {
        // given
        String writer = "test1";
        String title = "test2";
        String contents = "test3";
        Article article = new Article(writer, title, contents);

        // when
        String title2 = "test22";
        String contents2 = "test33";
        article.updateContents(title2, contents2);

        // then
        assertThat(article.getTitle()).isEqualTo(title2);
        assertThat(article.getContents()).isEqualTo(contents2);
    }

    @Test
    @DisplayName("게시글 삭제")
    public void deleteArticle() throws Exception {
        // given
        String writer = "test1";
        String title = "test2";
        String contents = "test3";
        Article article = new Article(writer, title, contents);

        // when
        article.deleteEntity();

        // then
        assertThat(article.getIsDeleted()).isTrue();
    }
}
