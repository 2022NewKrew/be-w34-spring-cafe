package com.kakao.cafe.article.domain;

import com.kakao.cafe.user.domain.UserId;
import com.kakao.cafe.util.ErrorCode;
import com.kakao.cafe.util.exception.ArticleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArticleTest {

    @Test
    @DisplayName("Article이 Null인 경우 ArticleId를 생성할 수 없다.")
    void invalidNull() {
        assertThatThrownBy(() -> new Article(1L, null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    @DisplayName("Article 생성 확인")
    void createArticle() {
        Article article = new Article(new UserId("test123"), new Date(), new Title("123456"), new Contents("테스트용"));
        new Article(0L, article);
    }
}
