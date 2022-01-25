package com.kakao.cafe.domain.article;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    Article article = new Article(0,"Spring MVC란?","Spring MVC에 대해 알려주세요.","unknown","2021-01-18",0);

    @Test
    void getTitle(){
        assertEquals("Spring MVC란?",article.getTitle());
    }
}