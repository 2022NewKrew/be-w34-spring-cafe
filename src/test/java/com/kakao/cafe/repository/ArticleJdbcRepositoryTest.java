package com.kakao.cafe.repository;

import com.kakao.cafe.domain.article.dto.ArticleSaveForm;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleJdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ArticleJdbcRepositoryTest {
    @Autowired
    private ArticleJdbcRepository articleJdbcRepository;

    @Test
    void save() {
        // given
        ArticleSaveForm articleSaveForm = new ArticleSaveForm();
        articleSaveForm.setContent("content");
        articleSaveForm.setTitle("title");
        articleSaveForm.setAuthor("me");

        // when
        Long savedId = articleJdbcRepository.save(articleSaveForm);

        // then
        Article byId = articleJdbcRepository.findById(savedId);
        assertThat(byId.getArticleId()).isEqualTo(1L);
        assertThat(byId.getContent()).isEqualTo("content");
        assertThat(byId.getTitle()).isEqualTo("title");
    }
}
