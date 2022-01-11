package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleRepositoryTest {
    ArticleRepository articleRepository = ArticleRepository.getRepository();

    @BeforeEach
    void init() {
        articleRepository.clear();
    }

    @Test
    @DisplayName("게시글 저장과 조회 확인")
    void testArticleSaveAndFind() throws Exception {
        // given
        String title = "질문이 있습니다.";
        Long id = 20L;
        String contents ="테스트는 어떻게 작성하나요";
        CreateArticleRequestDTO dto = new CreateArticleRequestDTO(title, id, contents);

        // when
        articleRepository.persist(dto);
        Article article = articleRepository.find(0L);

        // then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getAuthorId()).isEqualTo(id);
        assertThat(article.getContents()).isEqualTo(contents);
    }
}
