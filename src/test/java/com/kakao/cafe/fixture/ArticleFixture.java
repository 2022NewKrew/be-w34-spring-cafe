package com.kakao.cafe.fixture;

import static com.kakao.cafe.fixture.UserFixture.USER1;
import static com.kakao.cafe.fixture.UserFixture.USER2;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.MultipleArticle;
import com.kakao.cafe.article.dto.SingleArticle;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleFixture {

    public static final Article ARTICLE1 = Article.builder()
        .id(1L)
        .title("title1")
        .body("body body body")
        .createdAt(LocalDateTime.now())
        .authorId(1L)
        .build();

    public static final Article ARTICLE2 = Article.builder()
        .id(2L)
        .title("title2")
        .body("body body body")
        .createdAt(LocalDateTime.now())
        .authorId(1L)
        .build();

    public static final Article ARTICLE3 = Article.builder()
        .id(3L)
        .title("title3")
        .body("body body body")
        .createdAt(LocalDateTime.now())
        .authorId(2L)
        .build();

    public static final List<MultipleArticle> ARTICLES = List.of(
        MultipleArticle.of(ARTICLE1, USER1),
        MultipleArticle.of(ARTICLE2, USER1),
        MultipleArticle.of(ARTICLE3, USER2)
    );

    public static final SingleArticle SINGLE_ARTICLE1 = SingleArticle.of(ARTICLE1, USER1);
}
