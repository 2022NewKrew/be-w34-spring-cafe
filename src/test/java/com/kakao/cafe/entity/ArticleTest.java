package com.kakao.cafe.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private static final Integer articleId = 1;
    private static final String title = "testTitle";
    private static final String content = "testContent";

    @Test
    @DisplayName("[성공] 게시글 등록")
    void create_Article() {
        // given
        Article article = new Article(1, "제목", "내용");

    }

    @Test
    @DisplayName("[실패] 게시글 등록")
    void create_Article_Fail() {
        // given
        Article article = new Article(1, null, "내용");

        // when
//        ar
    }
}