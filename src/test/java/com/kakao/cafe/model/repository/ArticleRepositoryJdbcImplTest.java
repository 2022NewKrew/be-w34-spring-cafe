package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ArticleRepositoryJdbcImplTest {
    private final ArticleRepository articleRepository;
    private final Logger logger = LoggerFactory.getLogger(ArticleRepositoryJdbcImplTest.class);

    @Autowired
    public ArticleRepositoryJdbcImplTest(DataSource dataSource) {
        articleRepository = new ArticleRepositoryJdbcImpl(dataSource);
    }

    @Test
    @DisplayName("원하는 개수 만큼 게시글을 가져오는지 확인")
    public void findArticlesByWantedCountTest() {
        long start = 10;
        long count = 15;
        List<Article> articleList = articleRepository.findArticlesByStartAndWantedCountPerPage(start, count);

        assertThat(articleList.size()).isLessThanOrEqualTo((int) count);

        for (Article article : articleList) {
            logger.debug(article.toString());
        }
    }

    @Test
    @DisplayName("게시글의 개수를 정확하게 가져오는지 확인")
    public void countAllAvailableArticlesTest() {
        List<Article> articleList = articleRepository.findAllArticle();
        long totalCount = articleRepository.countAllAvailableArticles();

        assertThat(totalCount).isEqualTo(articleList.size());
    }
}