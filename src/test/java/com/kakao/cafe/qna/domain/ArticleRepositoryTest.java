package com.kakao.cafe.qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleRepositoryTest {
    private ArticleRepository articleRepository;

    @BeforeEach
    void SetUp() {
        articleRepository = new ArticleRepository();
    }

    @Test
    void submitArticleBasic() {
        List<Article> articleList = articleRepository.getArticleLst();
        assertThat(articleList.size()).isEqualTo(0);
        articleRepository.submitArticle("faust", "titleA", "contentA");
        assertThat(articleList.size()).isEqualTo(1);

        Article submitArticle = articleList.get(0);

        assertThat(submitArticle.getId()).isEqualTo(1);
    }

    @Test
    void submitArticleConcurrent() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    articleRepository.submitArticle("faust", String.valueOf(i), "content");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    articleRepository.submitArticle("faust", String.valueOf(i), "content");
                }
            }
        }, "Thread-2");

        List<Article> articleList = articleRepository.getArticleLst();
        assertThat(articleList.size()).isEqualTo(0);

        //TODO Concurrent submit
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertThat(articleList.size()).isEqualTo(200);

        for (int i = 0; i < 200; i++) {
            assertThat(articleList.get(i).getId()).isEqualTo(i + 1);
        }
    }
}