package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleServiceTest {
    private final ArticleService articleService;
    private final UserService userService;

    @Autowired
    ArticleServiceTest(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @Test
    void update() {
        User user = new User("test123", "test1234", "One Test", "test1@test.com");
        long userId = userService.create(user);
        user.setId(userId);

        Article article = new Article();
        article.setTitle("test1");
        article.setContent("test123");
        article.setAuthor(user);
        long articleId = articleService.create(article);
        article.setId(articleId);

        article.setTitle("test2");
        article.setContent("test456");
        articleService.update(article);

        Article fetch = articleService.fetch(articleId);
        Assertions.assertEquals("test2", fetch.getTitle());
        Assertions.assertEquals("test456", fetch.getContent());
    }
}