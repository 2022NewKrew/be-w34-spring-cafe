package com.kakao.cafe.adapter.out.infra.persistence.article;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.application.article.dto.UpdateArticleRequest;
import com.kakao.cafe.application.article.dto.WriteArticleRequest;
import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
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
        WriteArticleRequest writeArticleRequest = new WriteArticleRequest.Builder().userId("kakao")
                                                                                   .writer("champ")
                                                                                   .title("test")
                                                                                   .contents("testing")
                                                                                   .build();

        // then
        assertThatNoException().isThrownBy(() -> articleAdapter.registerArticle(writeArticleRequest));
    }

    @DisplayName("작성자 이름 누락 article 테스트")
    @Test
    void postNullWriterArticle() {
        // given
        WriteArticleRequest writeArticleRequest = new WriteArticleRequest.Builder().userId("kakao")
                                                                                   .writer("")
                                                                                   .title("test")
                                                                                   .contents("testing")
                                                                                   .build();

        // then
        assertThatExceptionOfType(IllegalWriterException.class)
            .isThrownBy(() -> articleAdapter.registerArticle(writeArticleRequest));
    }

    @DisplayName("작성자 이름 공백 article 테스트")
    @Test
    void postBlankWriterArticle() {
        // given
        WriteArticleRequest writeArticleRequest = new WriteArticleRequest.Builder().userId("kakao")
                                                                                   .writer("cha mp")
                                                                                   .title("test")
                                                                                   .contents("testing")
                                                                                   .build();

        // then
        assertThatExceptionOfType(IllegalWriterException.class)
            .isThrownBy(() -> articleAdapter.registerArticle(writeArticleRequest));
    }

    @DisplayName("제목 누락 article 테스트")
    @Test
    void postNullTitleArticle() {
        // given
        WriteArticleRequest writeArticleRequest = new WriteArticleRequest.Builder().userId("kakao")
                                                                                   .writer("champ")
                                                                                   .title("")
                                                                                   .contents("testing")
                                                                                   .build();

        // then
        assertThatExceptionOfType(IllegalTitleException.class)
            .isThrownBy(() -> articleAdapter.registerArticle(writeArticleRequest));
    }

    @DisplayName("sessionId 누락 article 테스트")
    @Test
    void postNullUserIdArticle() {
        // given
        WriteArticleRequest writeArticleRequest = new WriteArticleRequest.Builder().userId("")
                                                                                   .writer("champ")
                                                                                   .title("test")
                                                                                   .contents("testing")
                                                                                   .build();

        // then
        assertThatExceptionOfType(IllegalUserIdException.class)
            .isThrownBy(() -> articleAdapter.registerArticle(writeArticleRequest));
    }

    @DisplayName("게시글 찾기 성공 테스트")
    @Test
    void findArticleSuccess()
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, ArticleNotExistException, IllegalUserIdException {
        // given
        int givenId = 1;
        Article givenArticle = new Article.Builder().userId("test")
                                                    .writer("champ")
                                                    .title("kakao")
                                                    .contents("Hello Kakao")
                                                    .createdAt(
                                                        LocalDateTime.now()
                                                                     .format(
                                                                         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                                                                     )
                                                    )
                                                    .deleted(false)
                                                    .build();
        givenArticle.setId(givenId);
        given(articleRepository.findById(givenId)).willReturn(Optional.of(givenArticle));

        // when
        Article article = articleAdapter.findArticleById(givenId);

        assertThat(givenArticle.getId()).isEqualTo(article.getId());
        assertThat(givenArticle.getUserId()).isEqualTo(article.getUserId());
        assertThat(givenArticle.getWriter()).isEqualTo(article.getWriter());
        assertThat(givenArticle.getTitle()).isEqualTo(article.getTitle());
        assertThat(givenArticle.getContents()).isEqualTo(article.getContents());
        assertThat(givenArticle.getCreatedAt()).isEqualTo(article.getCreatedAt());
    }

    @DisplayName("게시글 찾기 실패 테스트")
    @Test
    void findArticleFail()
        throws IllegalWriterException, IllegalTitleException, IllegalDateException, IllegalUserIdException {
        // given
        int givenId = 1;
        Article givenArticle = new Article.Builder().userId("test")
                                                    .writer("champ")
                                                    .title("kakao")
                                                    .contents("Hello Kakao")
                                                    .createdAt(
                                                        LocalDateTime.now()
                                                                     .format(
                                                                         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                                                                     )
                                                    )
                                                    .deleted(false)
                                                    .build();
        givenArticle.setId(givenId);
        given(articleRepository.findById(givenId)).willReturn(Optional.empty());

        // then
        assertThatExceptionOfType(ArticleNotExistException.class)
            .isThrownBy(() -> articleAdapter.findArticleById(givenId));
    }

    @DisplayName("게시글 수정 성공 테스트")
    @Test
    void updateArticleSuccess() {
        // given
        int givenId = 1;
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest.Builder().id(givenId)
                                                                                      .userId("kakao")
                                                                                      .writer("champ")
                                                                                      .title("hello")
                                                                                      .contents("hello kakao")
                                                                                      .build();

        // then
        assertThatNoException().isThrownBy(() -> articleAdapter.updateArticle(updateArticleRequest));
    }

    @DisplayName("제목 누락 게시글 수정 실패 테스트")
    @Test
    void updateNullTitleArticleFail() {
        // given
        int givenId = 1;
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest.Builder().id(givenId)
                                                                                      .userId("kakao")
                                                                                      .writer("champ")
                                                                                      .title("")
                                                                                      .contents("hello kakao")
                                                                                      .build();

        // then
        assertThatExceptionOfType(IllegalTitleException.class)
            .isThrownBy(() -> articleAdapter.updateArticle(updateArticleRequest));
    }

    @DisplayName("userId 누락 게시글 수정 실패 테스트")
    @Test
    void updateNullUserIdArticleFail() {
        // given
        int givenId = 1;
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest.Builder().id(givenId)
                                                                                      .userId("")
                                                                                      .writer("champ")
                                                                                      .title("hello")
                                                                                      .contents("hello kakao")
                                                                                      .build();

        // then
        assertThatExceptionOfType(IllegalUserIdException.class)
            .isThrownBy(() -> articleAdapter.updateArticle(updateArticleRequest));
    }

    @DisplayName("작성자 누락 게시글 수정 실패 테스트")
    @Test
    void updateNullWriterArticleFail() {
        // given
        int givenId = 1;
        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest.Builder().id(givenId)
                                                                                      .userId("kakao")
                                                                                      .writer("")
                                                                                      .title("hello")
                                                                                      .contents("hello kakao")
                                                                                      .build();

        // then
        assertThatExceptionOfType(IllegalWriterException.class)
            .isThrownBy(() -> articleAdapter.updateArticle(updateArticleRequest));
    }

    @DisplayName("게시글 삭제 성공 테스트")
    @Test
    void deleteArticleSuccess() {
        // given
        int id = 5;

        // then
        assertThatNoException().isThrownBy(() -> articleAdapter.delete(id));
    }
}
