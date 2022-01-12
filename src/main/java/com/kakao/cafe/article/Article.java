package com.kakao.cafe.article;

import com.kakao.cafe.user.User;
import com.kakao.cafe.user.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Article {
    // Temporary workaround
    private static UserService userService = TemporaryBeanContextUtil.getBean(UserService.class);

    // Simulate in-memory database
    private static final List<Article> articles = new ArrayList<>();
    private static Long next_id = 0L;
    // Simulate in-memory database


    private final Long id;
    private final String title;
    private final String content;
    private final User author;
    private final LocalDateTime created;
    private final LocalDateTime modified;

    public Article(String title, String content, String author_username) {
        this.title = title;
        this.content = content;


        // Simulate in-memory database
        // Assumes Article() is called only on posting
        id = next_id++;
        // User following line in later View DTO
        // created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
        author = userService.findUserByUsername(author_username);
        // Simulate in-memory database
    }


    // Simulate in-memory database
    public static void addArticle(Article article) {
        articles.add(article);
    }

    public static List<Article> getArticles() {
        return articles;
    }

    public static Article findArticleById(Long id) {
        return articles.stream().filter(article -> article.id.equals(id)).findFirst().orElseThrow(
                () -> new NoSuchElementException("Article id not found: " + id));
    }
    // Simulate in-memory database


    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", title='" + title + '\'' + ", content='" + content + '\'' + ", author=" + author + ", created=" + created + ", modified=" + modified + '}';
    }
}
