package com.kakao.cafe.articles;

import com.kakao.cafe.CafeApplicationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryArticleRepositoryTest extends CafeApplicationTests {
    ArticleRepository articleRepository = new MemoryArticleRepository();

    @Test
    void 저장() {
        Article given = articles.get(0);

        Article actual = articleRepository.save(given);

        Assertions.assertThat(given).isEqualTo(actual);
    }

    @Test
    void 게시글하나찾기() {
        Article given = articles.get(1);
        articleRepository.save(given);

        Article actual = articleRepository.findById(given.getId()).get();

        Assertions.assertThat(given).isEqualTo(actual);
    }

    @Test
    void 게시글전체찾기() {
        for(Article given : articles) {
            articleRepository.save(given);
        }

        List<Article> actual = articleRepository.findAll();

        Assertions.assertThat(actual).hasSize(2);
    }

    @Test
    void getSize() {
        Article given = articles.get(1);
        articleRepository.save(given);

        int actual = articleRepository.getSize();

        Assertions.assertThat(actual).isEqualTo(1);
    }
}