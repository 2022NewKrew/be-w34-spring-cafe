package com.kakao.cafe.adapter.out.infra.persistence.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.application.article.dto.WriteRequest;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArticleAdapterTest {

    @Mock
    ArticleRepository articleRepository;

    @InjectMocks
    ArticleAdapter articleAdapter;

    @DisplayName("게시글 정상 등록")
    @Test
    void postNormalArticle() {
        // given
        WriteRequest writeRequest = new WriteRequest("kakao", "Hello Kakao!");
        writeRequest.setWriter("champ");

        // then
        assertThatNoException().isThrownBy(() -> articleAdapter.registerArticle(writeRequest));
    }

    @DisplayName("작성자 이름 누락 article 테스트")
    @Test
    void postNullWriterArticle() {
        // given
        WriteRequest writeRequest = new WriteRequest("kakao", "Hello Kakao!");
        writeRequest.setWriter("");

        // then
        assertThatExceptionOfType(IllegalWriterException.class).isThrownBy(() -> articleAdapter.registerArticle(
            writeRequest));
    }

    @DisplayName("작성자 이름 공백 article 테스트")
    @Test
    void postBlankWriterArticle() {
        // given
        WriteRequest writeRequest = new WriteRequest("kakao", "Hello Kakao!");
        writeRequest.setWriter("cha mp");

        // then
        assertThatExceptionOfType(IllegalWriterException.class).isThrownBy(() -> articleAdapter.registerArticle(
            writeRequest));
    }

    @DisplayName("제목 누락 article 테스트")
    @Test
    void postNullTitleArticle() {
        // given
        WriteRequest writeRequest = new WriteRequest("", "Hello Kakao!");
        writeRequest.setWriter("champ");

        // then
        assertThatExceptionOfType(IllegalTitleException.class).isThrownBy(() -> articleAdapter.registerArticle(
            writeRequest));
    }

    @DisplayName("게시글 찾기 성공 테스트")
    @Test
    void findArticleSuccess()
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, ArticleNotExistException {
        // given
        int givenId = 1;
        Article givenArticle = new Article.Builder().writer("champ")
                                                    .title("kakao")
                                                    .contents("Hello Kakao")
                                                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                                                        "yyyy-MM-dd HH:mm")))
                                                    .build();
        givenArticle.setId(givenId);
        given(articleRepository.findById(givenId)).willReturn(Optional.of(givenArticle));

        // when
        Article article = articleAdapter.findArticleById(givenId);

        assertThat(givenArticle.getId()).isEqualTo(article.getId());
        assertThat(givenArticle.getWriter()).isEqualTo(article.getWriter());
        assertThat(givenArticle.getTitle()).isEqualTo(article.getTitle());
        assertThat(givenArticle.getContents()).isEqualTo(article.getContents());
        assertThat(givenArticle.getCreatedAt()).isEqualTo(article.getCreatedAt());
    }

    @DisplayName("게시글 찾기 실패 테스트")
    @Test
    void findArticleFail()
        throws IllegalWriterException, IllegalTitleException, IllegalDateException {
        // given
        int givenId = 1;
        Article givenArticle = new Article.Builder().writer("champ")
                                                    .title("kakao")
                                                    .contents("Hello Kakao")
                                                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern(
                                                        "yyyy-MM-dd HH:mm")))
                                                    .build();
        givenArticle.setId(givenId);
        given(articleRepository.findById(givenId)).willReturn(Optional.empty());

        // then
        assertThatExceptionOfType(ArticleNotExistException.class).isThrownBy(() -> articleAdapter.findArticleById(
            givenId));
    }
}
