package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.ArticleDaoPort;
import com.kakao.cafe.domain.article.ArticleVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
class WriteFindArticleServiceTest {

    @InjectMocks
    WriteArticleService writeArticleService;

    @Mock
    ArticleDaoPort articlePort;

    @DisplayName("회원은 글 작성을 할 수 있다")
    @Test
    void checkWriteArticleSuccessfully() {
        // given
        ArticleVo articleVo = new ArticleVo("윤이진", "Hello", "World");

        // when
        writeArticleService.write(articleVo);

        //then
        verify(articlePort).save(any(ArticleVo.class));
    }

}