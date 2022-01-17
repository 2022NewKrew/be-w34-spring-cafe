package com.kakao.cafe.domain.article;

import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.H2ArticleRepository;
import com.kakao.cafe.web.article.dto.ArticleCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
public class ArticleRepositoryTest {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleRepositoryTest(JdbcTemplate jdbcTemplate) {
        articleRepository = new H2ArticleRepository(jdbcTemplate);
    }

    @DisplayName("저장소 게시글 저장 테스트")
    @MethodSource("provideArticle")
    @ParameterizedTest
    public void testArticleSave(Title title, Content content) {
        //given
        final ArticleCreateRequest dto = new ArticleCreateRequest(title, content);
        Article article = dto.toEntity();
        article.setId(1);

        //when
        int articleId = articleRepository.save(article);

        //then
        Article saved = articleRepository.findById(articleId);
        assertThat(articleRepository.findById(articleId)).isEqualTo(article);
        assertThat(articleRepository.findAll()).isEqualTo(List.of(article));
    }

    private static Stream<Arguments> provideArticle() {
        return Stream.of(
                Arguments.of(new Title("title"), new Content("content"))
        );
    }
}
