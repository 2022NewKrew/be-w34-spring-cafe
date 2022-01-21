package com.kakao.cafe.service.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("사용자가 게시글을 작성한다.")
    void write() {
        // given
        String writerId = "jeuslhg";
        String title = "안녕하세요";
        String contents = "내용";

        // when
        Long writeArticleId = articleService.write(writerId, title, contents);

        // then
        assertThat(writeArticleId).isGreaterThan(0L);
    }
}