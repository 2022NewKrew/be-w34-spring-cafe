package com.kakao.cafe.domain;

import com.kakao.cafe.utility.ArticleException;
import com.kakao.cafe.utility.ErrorCode;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArticleTest {

    @Test
    void createArticlef() {
        User user = new User(new UserId("test123"), new Password("&test12345"), new Name("테스트"), new Email("test123@test.com"));
        new Article(user, new Date(), new Title("123456"), new Contents("테스트용"));
    }

    @Test
    void createArticlwee() {
        User user = new User(new UserId("test123"), new Password("&test12345"), new Name("테스트"), new Email("test123@test.com"));
        Article article = new Article(user, new Date(), new Title("123456"), new Contents("테스트용"));

        assertThatThrownBy(() -> new Article(0L, null))
                .isInstanceOf(ArticleException.class)
                .hasMessageMatching(ErrorCode.INVALID_NULL_VALUE.getErrorMessage());
    }

    @Test
    void createArticler3() {
        User user = new User(new UserId("test123"), new Password("&test12345"), new Name("테스트"), new Email("test123@test.com"));
        Article article = new Article(user, new Date(), new Title("123456"), new Contents("테스트용"));
        Article articleContainsId = new Article(0L, article);
    }
}
