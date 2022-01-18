package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    MockHttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
    }

    @AfterEach
    void clear() {
        session.invalidate();
    }

    @Test
    @DisplayName("세션 없을시 NotSessionInfo error테스트")
    void findById() {

        Throwable exception = assertThrows(RuntimeException.class, () -> {
            articleService.findById("test", session);
        });
        assertEquals("글을 읽으려면 로그인을 하십시오", exception.getMessage());
    }

    @DisplayName("글 작성자가 아닌 user가 글 수정,삭제시 error테스트")
    @Test
    void isWriter() {
        User user = new User();
        user.setUserId("testWriter1");
        session.setAttribute("curUser", user);

        Article article = new Article();
        article.setWriter("testWriter2");

        Throwable exception = assertThrows(RuntimeException.class, () -> {
            articleService.isWriter(article, session);
        });
        assertEquals("다른 사람의 글을 수정, 삭제할 수 없습니다.", exception.getMessage());
    }
}