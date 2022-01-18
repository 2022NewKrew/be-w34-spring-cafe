package com.kakao.cafe.articles;

import com.kakao.cafe.CafeApplicationTests;
import com.kakao.cafe.exceptions.NotFoundException;
import com.kakao.cafe.users.UserDto;
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
class ArticleServiceTest extends CafeApplicationTests {
    @Mock
    MemoryArticleRepository articleRepository;

    @InjectMocks
    ArticleService articleService;

    @Test
    void 게시글생성해보자() {
        Article givenArticle = articles.get(0);

        given(articleRepository.getSize()).willReturn(1);
        given(articleRepository.save(any())).willReturn(articles.get(0));

        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setContent(givenArticle.getContent().getBody());
        articleRequest.setTitle(givenArticle.getTitle());
        articleRequest.setWriter(givenArticle.getWriter());

        UserDto userDto = new UserDto(givenArticle.getWriterId(), "test", givenArticle.getWriter(), "test");

        ArticleDto actual = articleService.createArticle(articleRequest, userDto);

        Assertions.assertThat(actual.getId()).isEqualTo(givenArticle.getId());
        Assertions.assertThat(actual.getContent()).isEqualTo(givenArticle.getContent().getBody());
        Assertions.assertThat(actual.getTitle()).isEqualTo(givenArticle.getTitle());

    }

    @Test
    void 없는게시글을찾을떄익셉션() {
        given(articleRepository.findById(any())).willReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> articleService.getArticleById(3L)).isInstanceOf(NotFoundException.class);
    }
}