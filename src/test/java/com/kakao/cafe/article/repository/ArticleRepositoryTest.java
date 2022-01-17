package com.kakao.cafe.article.repository;

import com.kakao.cafe.article.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleRepositoryTest {
    @Autowired
    private ArticleDBRepositoryImpl articleRepository;


    @Test
    @DisplayName("게시글 저장과 조회 확인")
    void testArticleSaveAndFind() throws Exception {
        // given
        String title = "질문이 있습니다.";
        Long id = 20L;
        String contents ="테스트는 어떻게 작성하나요";
        ArticleCreateRequestDTO dto = new ArticleCreateRequestDTO(title, id, contents);

        // when
        Long id1 = articleRepository.persist(dto);
        Article article = articleRepository.find(id1).get();

        // then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getAuthorId()).isEqualTo(id);
        assertThat(article.getContents()).isEqualTo(contents);
    }
}
