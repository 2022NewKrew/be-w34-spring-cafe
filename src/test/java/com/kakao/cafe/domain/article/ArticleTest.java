package com.kakao.cafe.domain.article;

import com.kakao.cafe.web.dto.ArticleDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    @Mock
    Article article = new Article(new ArticleDTO("Spring MVC란?","Spring MVC에 대해 알려주세요."));

    @Test
    void getTitle(){
        assertEquals("Spring MVC란?",article.getTitle());
    }
}