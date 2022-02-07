package com.kakao.cafe.domain.article;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    Article article = Article.builder()
            .id(0)
            .title("Spring MVC란?")
            .content("Spring MVC에 대해 알려주세요.")
            .createUserId("unknown")
            .createDate("2021-01-18")
            .views(0)
            .build();

    @Test
    void getTitle(){
        assertEquals("Spring MVC란?",article.getTitle());
    }
}