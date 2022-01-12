package com.kakao.cafe.application;

import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.article.ArticleVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.verify;

class ArticleServiceTest {

    @InjectMocks
    ArticleService articleService;

    @Mock
    ArticlePort articlePort;

    @DisplayName("회원은 글 작성을 할 수 있다")
    @Test
    void checkWriteArticleSuccessfully() {
        // given
        ArticleVo articleVo = new ArticleVo("윤이진", "Hello", "World");

        // when
        articleService.write(articleVo);

        //then
        verify(articlePort).save(any(ArticleVo.class));
    }

}