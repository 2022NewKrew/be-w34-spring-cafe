package com.kakao.cafe.fixture;

import static com.kakao.cafe.fixture.UserFixture.USER1;
import static com.kakao.cafe.fixture.UserFixture.USER2;

import com.kakao.cafe.article.domain.Comment;
import com.kakao.cafe.article.dto.SingleComment;
import java.time.LocalDateTime;
import java.util.List;

public class CommentFixture {

    public static final Comment COMMENT1 = Comment.builder()
        .id(1L)
        .articleId(1L)
        .body("comment body")
        .createdAt(LocalDateTime.now())
        .authorId(1L)
        .build();

    public static final Comment COMMENT2 = Comment.builder()
        .id(2L)
        .articleId(1L)
        .body("comment body")
        .createdAt(LocalDateTime.now())
        .authorId(1L)
        .build();

    public static final Comment COMMENT3 = Comment.builder()
        .id(3L)
        .articleId(1L)
        .body("comment body")
        .createdAt(LocalDateTime.now())
        .authorId(2L)
        .build();

    public static final List<SingleComment> COMMENTS = List.of(
        SingleComment.of(COMMENT1, USER1),
        SingleComment.of(COMMENT2, USER1),
        SingleComment.of(COMMENT3, USER2)
    );
}
