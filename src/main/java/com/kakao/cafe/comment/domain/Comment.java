package com.kakao.cafe.comment.domain;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Comment {

    @Setter
    private int id;

    @NonNull
    private int articleId;

    @NonNull
    private String authorId;

    @NonNull
    private String content;

    @NonNull
    private String createdAt;

    @NonNull
    private User author;

    @NonNull
    private Article article;
}
