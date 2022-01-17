package com.kakao.cafe.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleRegistrationDtoTest {

    private static final Integer articleId = 1;
    private static final String title = "testTitle";
    private static final String content = "testContent";

    @Test
    @DisplayName("[확인] article_Title_null_검증_시점")
    void article_Title_Null() {
        // given
        new ArticleRegistrationDto(1, null, "abc");
    }
}