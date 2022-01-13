package com.kakao.cafe.articles;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcArticleRepositoryTest {
    @Autowired
    JdbcArticleRepository articleRepository;

    List<Article> articles = List.of(
            new Article(1L, "title", new ArticleContent("content"), "me"),
            new Article(2L, "title2", new ArticleContent("content2"), "me2")
    );

    @Test
    void 저장() {
        Article given = articles.get(0);

        Article actual = articleRepository.save(given);

        Assertions.assertThat(given).isEqualTo(actual);
    }

    @Test
    void 아티클하나() {
        Article given = articles.get(0);
        articleRepository.save(given);

        Article actual = articleRepository.findById(given.getId()).get();

        Assertions.assertThat(given).isEqualTo(actual);
    }

    @Test
    void 아티클전체() {
        articleRepository.save(articles.get(0));
        articleRepository.save(articles.get(1));

        List<Article> actual = articleRepository.findAll();

        Assertions.assertThat(actual).hasSize(articles.size());
    }

    @Test
    void 카운팅() {
        articleRepository.save(articles.get(0));

        int actual = articleRepository.getSize();

        Assertions.assertThat(actual).isEqualTo(1);
    }
}