package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

import com.kakao.cafe.dto.ReplyDTO.Create;
import com.kakao.cafe.dto.ReplyDTO.Update;
import com.kakao.cafe.error.exception.ForbiddenAccessException;
import com.kakao.cafe.persistence.model.Article;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.Reply;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.ArticleRepository;
import com.kakao.cafe.persistence.repository.ReplyRepository;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ReplyServiceTest {

    @MockBean
    private ReplyRepository replyRepository;

    @MockBean
    private ArticleRepository articleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ReplyService replyService;

    @Test
    @DisplayName("올바른 인증 정보를 통한 댓글 달기 테스트")
    void create() {
        // Given
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        Article article = Article.builder().id(1L).uid("uid").title("title").body("body")
            .createdAt(LocalDateTime.now()).build();
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));
        given(articleRepository.findArticleById(any()))
            .willReturn(Optional.of(article));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        Long articleId = 1L;
        Create createDTO = new Create("body");
        assertDoesNotThrow(() -> replyService.create(authInfo, articleId, createDTO));

        // Then
        then(replyRepository)
            .should()
            .save(any());
    }

    @Test
    @DisplayName("Article 별 댓글 조회 테스트")
    void readByArticleId() {
        // Given

        // When
        replyService.readByArticleId(1L);

        // Then
        then(replyRepository)
            .should()
            .findByArticleId(any());
    }

    @Test
    @DisplayName("댓글 ID 조회 테스트")
    void readById() {
        // Given
        given(replyRepository.findById(any()))
            .willReturn(Optional.of(
                Reply.builder().id(1L).articleId(1L).uid("uid").userName("name").body("body")
                    .createdAt(LocalDateTime.now()).build()));

        // When
        replyService.readById(1L);

        // Then
        then(replyRepository)
            .should()
            .findById(any());
    }

    @Test
    @DisplayName("올바른 인증 정보를 통한 댓글 수정 테스트")
    void update() {
        // Given
        given(replyRepository.findById(any()))
            .willReturn(Optional.of(Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body").createdAt(LocalDateTime.now()).build()));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        Long id = 1L;
        Update updateDTO = new Update("modified");
        replyService.update(authInfo, id, updateDTO);

        // Then
        then(replyRepository)
            .should()
            .update(any(), any());
    }

    @Test
    @DisplayName("올바르지 않은 인증 정보를 통한 댓글 수정 테스트")
    void update2() {
        // Given
        given(replyRepository.findById(any()))
            .willReturn(Optional.of(Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body").createdAt(LocalDateTime.now()).build()));

        // When
        AuthInfo authInfo = AuthInfo.of("uid1");
        Long id = 1L;
        Update updateDTO = new Update("modified");
        ForbiddenAccessException exception = assertThrows(ForbiddenAccessException.class,
            () -> replyService.update(authInfo, id, updateDTO));

        // Then
        assertThat(exception.getMessage())
            .contains("Forbidden Access");
        then(replyRepository)
            .should(never())
            .update(any(), any());
    }

    @Test
    @DisplayName("올바른 인증 정보를 통한 댓글 삭제 테스트")
    void delete() {
        // Given
        given(replyRepository.findById(any()))
            .willReturn(Optional.of(Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body").createdAt(LocalDateTime.now()).build()));

        // When
        AuthInfo authInfo = AuthInfo.of("uid");
        Long id = 1L;
        replyService.delete(authInfo, id);

        // Then
        then(replyRepository)
            .should()
            .delete(any());
    }

    @Test
    @DisplayName("올바르지 않은 정보를 통한 댓글 삭제 테스트")
    void delete2() {
        // Given
        given(replyRepository.findById(any()))
            .willReturn(Optional.of(Reply.builder().id(1L).articleId(1L).uid("uid").userName("name")
                .body("body").createdAt(LocalDateTime.now()).build()));

        // When
        AuthInfo authInfo = AuthInfo.of("uid1");
        Long id = 1L;
        ForbiddenAccessException exception = assertThrows(ForbiddenAccessException.class,
            () -> replyService.delete(authInfo, id));

        // Then
        assertThat(exception.getMessage())
            .contains("Forbidden Access");
        then(replyRepository)
            .should(never())
            .delete(any());
    }
}