package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ArticleCreateRequestTest {
    @DisplayName("article toEntity 테스트")
    @Test
    public void testArticleCreateRequestDto() {
        //given
        final Title title = new Title("title");
        final Content content = new Content("content");
        final ArticleCreateRequest dto = new ArticleCreateRequest(title, content);

        //when
        final Article article = dto.toEntity();

        //then
        assertThat(article.getTitle()).isEqualTo(title);
        assertThat(article.getContent()).isEqualTo(content);
    }
}