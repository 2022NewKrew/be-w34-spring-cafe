package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleRepository;
import com.kakao.cafe.web.article.dto.ArticleCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleFindServiceTest {
    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleFindService articleFindService;

    @DisplayName("article id 검색 테스트")
    @MethodSource("provideArticle")
    @ParameterizedTest
    public void findArticleId(String title, String content) {
        //given
        final ArticleCreateRequest dto = new ArticleCreateRequest(title, content);
        final Article givenArticle = dto.toEntity();
        final int articleId = 1;

        given(articleRepository.findById(articleId)).willReturn(givenArticle);

        //when
        Article findArticle = articleFindService.findById(articleId);

        //then
        assertThat(findArticle).isEqualTo(givenArticle);
    }

    private static Stream<Arguments> provideArticle() {
        return Stream.of(
                Arguments.of("title", "content")
        );
    }
}