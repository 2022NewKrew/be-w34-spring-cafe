package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.dto.ArticleDTO.Create;
import com.kakao.cafe.error.exception.ArticleNotFoundException;
import com.kakao.cafe.error.exception.UserNotFoundException;
import com.kakao.cafe.persistence.model.Article;
import com.kakao.cafe.persistence.model.AuthInfo;
import com.kakao.cafe.persistence.model.User;
import com.kakao.cafe.persistence.repository.ArticleRepository;
import com.kakao.cafe.persistence.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ArticleServiceTest {

    @MockBean
    private ArticleRepository articleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("존재하는 사용자의 게시글 생성 테스트")
    void create() {
        // Given
        AuthInfo authInfo = AuthInfo.of("uid");
        User user = User.builder().id(1L).uid("uid").password("pwd").name("name")
            .email("email@test.com").createdAt(LocalDateTime.now()).build();
        Create createDTO = new Create("title", "body");

        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.of(user));

        // When

        // Then
        assertDoesNotThrow(() -> articleService.create(createDTO, authInfo));
    }

    @Test
    @DisplayName("존재하지 않는 사용자의 게시글 생성 테스트")
    void create2() {
        // Given
        AuthInfo authInfo = AuthInfo.of("uid");
        given(userRepository.findUserByUid(any()))
            .willReturn(Optional.empty());

        // When
        Create createDTO = new Create("title", "body");
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
            () -> articleService.create(createDTO, authInfo));

        // Then
        assertThat(exception.getMessage())
            .contains("Not Found User");
    }

    @Test
    @DisplayName("Article Repository 정상 Read 테스트")
    void readById() {
        // Given
        Article article = Article.builder().id(1L).uid("uid").title("title").body("body")
            .createdAt(LocalDateTime.now()).build();
        given(articleRepository.findArticleById(any()))
            .willReturn(Optional.of(article));

        // When

        // Then
        assertDoesNotThrow(() -> articleService.readById(1L));
    }

    @Test
    @DisplayName("Article Repository 존재하지 않는 ID Read 테스트")
    void readById2() {
        // Given
        given(articleRepository.findArticleById(any()))
            .willReturn(Optional.empty());

        // When
        ArticleNotFoundException exception = assertThrows(ArticleNotFoundException.class,
            () -> articleService.readById(1L));

        // Then
        assertThat(exception.getMessage())
            .contains("Not Found Article");
    }
}