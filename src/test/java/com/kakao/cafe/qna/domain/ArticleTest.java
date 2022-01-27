package com.kakao.cafe.qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {
    private Article testingArticle;

    @BeforeEach
    void setUp() {
        testingArticle = new Article(1, "faust", "title", "content");
    }

    @Test
    void testId() {
        assertThat(testingArticle.getId()).isEqualTo(1);
    }


    @Test
    void testWriter() {
        assertThat(testingArticle.getWriter()).isEqualTo("faust");
    }

    @Test
    void testTitle() {
        assertThat(testingArticle.getTitle()).isEqualTo("title");
        testingArticle.setTitle("제목");
        assertThat(testingArticle.getTitle()).isEqualTo("제목");
    }

    @Test
    void testContent() {
        assertThat(testingArticle.getContent()).isEqualTo("content");
        testingArticle.setContent("내용");
        assertThat(testingArticle.getContent()).isEqualTo("내용");
    }

    @Test
    void testWrittenTime() {
        assertThat(testingArticle.getWrittenTime()).isEqualTo(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
    }

    @Test
    void getPoint() {
        assertThat(testingArticle.getPoint()).isEqualTo(0);

        testingArticle.increasePoint();
        assertThat(testingArticle.getPoint()).isEqualTo(1);

        testingArticle.decreasePoint();
        assertThat(testingArticle.getPoint()).isEqualTo(0);
    }
}