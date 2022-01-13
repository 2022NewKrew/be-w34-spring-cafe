package com.kakao.cafe.domain.article;

import static org.assertj.core.api.Assertions.assertThat;

import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArticlesTest {

    private Articles articles;

    @BeforeEach
    void setUp() {
        articles = new Articles();
    }

    @Test
    void add_Invoked_SetFieldOfArticleCorrectly() {
        User baseUser = new User(UUID.randomUUID(), new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        Article baseArticle = new Article(new Title("testTitle"), new Content("testContent"), baseUser);
        articles.add(baseArticle);

        assertThat(articles.getArticleList().get(0).getArticleId()).isNotNull();
        assertThat(articles.getArticleList().get(0).getCreatedAt()).isNotNull();
    }

    @Test
    void findByArticleId_ArticleExists_ReturnsCorrectOptional() {
        User baseUser = new User(UUID.randomUUID(), new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        Article baseArticle1 = new Article(new Title("testTitle1"), new Content("testContent1"), baseUser);
        Article baseArticle2 = new Article(new Title("testTitle2"), new Content("testContent2"), baseUser);
        articles.add(baseArticle1);
        articles.add(baseArticle2);

        UUID idToFind = baseArticle1.getArticleId();
        Optional<Article> actual = articles.findByArticleId(idToFind);

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().getTitle().getValue()).isEqualTo(baseArticle1.getTitle().getValue());
        assertThat(actual.get().getContent().getValue()).isEqualTo(baseArticle1.getContent().getValue());
    }

    @Test
    void findByArticleId_ArticleNotExist_ReturnsEmptyOptional() {
        User baseUser = new User(UUID.randomUUID(), new UserName("testUserName"), new Password("testPassword"), new Name("testName"), new Email("test@email.com"));
        Article baseArticle = new Article(new Title("testTitle"), new Content("testContent"), baseUser);
        articles.add(baseArticle);

        UUID idToFind;
        do {
            idToFind = UUID.randomUUID();
        } while (idToFind.equals(baseArticle.getArticleId()));
        Optional<Article> actual = articles.findByArticleId(idToFind);

        assertThat(actual.isPresent()).isFalse();
    }
}
