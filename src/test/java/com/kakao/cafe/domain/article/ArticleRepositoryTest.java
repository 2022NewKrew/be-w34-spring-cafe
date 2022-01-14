package com.kakao.cafe.domain.article;

import com.kakao.cafe.web.article.dto.ArticleCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ArticleRepositoryTest {
    private final ArticleRepository articleRepository = new InMemoryArticleRepository();

    @DisplayName("저장소 게시글 저장 테스트")
    @MethodSource("provideArticle")
    @ParameterizedTest
    public void testArticleSave(String title, String content) {
        //given
        final ArticleCreateRequest dto = new ArticleCreateRequest(title, content);
        Article article = dto.toEntity();

        //when
        int articleId = articleRepository.save(article);

        //then
        assertThat(articleRepository.findById(articleId)).isEqualTo(article);
        assertThat(articleRepository.findAll()).isEqualTo(List.of(article));
    }

    private static Stream<Arguments> provideArticle() {
        return Stream.of(
                Arguments.of("title", "content")
        );
    }
}
