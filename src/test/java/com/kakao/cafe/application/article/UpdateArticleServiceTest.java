package com.kakao.cafe.application.article;

import com.kakao.cafe.domain.article.ArticleVo;
import com.kakao.cafe.domain.article.UpdateArticlePort;
import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateArticleServiceTest {

    @InjectMocks
    private UpdateArticleService updateArticleService;

    @Mock
    private UpdateArticlePort updateArticlePort;

    @DisplayName("회원은 자신이 작성한 글을 수정할 수 있다.")
    @Test
    void checkUpdateArticle() {
        // given
        ArticleVo articleVo = new ArticleVo(1,
                new User("483759", "password", "윤이진", "483759@naver.com"),
                "Hello", "World");

        // when
        updateArticleService.update(articleVo);

        //then
        verify(updateArticlePort).update(articleVo);
    }

}