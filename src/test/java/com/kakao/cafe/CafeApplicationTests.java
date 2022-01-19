package com.kakao.cafe;

import com.kakao.cafe.articles.Article;
import com.kakao.cafe.articles.ArticleContent;
import com.kakao.cafe.replies.Reply;
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

    public List<Reply> replies = List.of(
            new Reply(1L, "content1", 1L, 1L, true),
            new Reply(1L, "content1", 1L, 2L, false),
            new Reply(1L, "content1", 2L, 1L, true)
    );

    @Test
    void contextLoads() {
    }

}
