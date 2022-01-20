package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.user.model.User;
import com.kakao.cafe.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;

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

    @Test
    void delete() {
        User user = new User("test123", "test1234", "One Test", "test1@test.com");
        long userId = userService.create(user);
        user.setId(userId);

        Article article = new Article();
        article.setTitle("test1");
        article.setContent("test123");
        article.setAuthor(user);
        long articleId = articleService.create(article);

        articleService.delete(articleId);

        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> articleService.fetch(articleId));
    }
}