package com.kakao.cafe;

import com.kakao.cafe.articles.Article;
import com.kakao.cafe.articles.ArticleContent;
import com.kakao.cafe.users.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CafeApplicationTests {
    public List<Article> articles = List.of(
            new Article(1L, "title", new ArticleContent("content"), "me", 1L),
            new Article(2L, "title2", new ArticleContent("content2"), "me2", 2L)
    );

    public List<User> users = List.of(
            new User("1234", "1234", "1234", "1234"),
            new User("asdf", "asdf", "asdf", "asdf")
    );

    @Test
    void contextLoads() {
    }

}
