package com.kakao.cafe.repository;

import com.kakao.cafe.controller.dto.ArticleDto;
import com.kakao.cafe.domain.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleJdbcRepositoryTest {
    @Autowired
    private ArticleJdbcRepository articleJdbcRepository;

    @Test
    void save() {
        // given
        ArticleDto articleDto = new ArticleDto();
        articleDto.setContent("content");
        articleDto.setTitle("title");
        articleDto.setWriter("me");

        // when
        Article article = Article.from(articleDto);
        Long savedId = articleJdbcRepository.save(article);

        // then
        Article byId = articleJdbcRepository.findById(savedId);
        assertThat(byId.getArticleId()).isEqualTo(1L);
        assertThat(byId.getContent()).isEqualTo("content");
        assertThat(byId.getTitle()).isEqualTo("title");
    }
}
