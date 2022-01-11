package com.kakao.cafe.articles;

import com.kakao.cafe.exceptions.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @Mock
    MemoryArticleRepository articleRepository;

    @InjectMocks
    ArticleService articleService;

    Article article = new Article(1L, "title", new ArticleContent("content"), "me");

    @Test
    void 없는게시글을찾을떄익셉션() {
        given(articleRepository.findById(any())).willReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> articleService.getArticleById(3L)).isInstanceOf(NotFoundException.class);
    }
}