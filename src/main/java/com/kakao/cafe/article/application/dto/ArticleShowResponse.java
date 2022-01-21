package com.kakao.cafe.article.application.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.comment.application.dto.CommentListResponse;
import com.kakao.cafe.user.domain.User;
import lombok.Builder;

import java.util.List;

@Builder
public class ArticleShowResponse {

    public final int articleId;
    public final String authorId;
    public final String authorName;
    public final String title;
    public final String content;
    public final String createdAt;
    public final List<CommentListResponse> comments;
    public final int commentCount;

    public static ArticleShowResponse valueOf(Article article, List<CommentListResponse> commentListResponses) {
        User author = article.getAuthor();
        return ArticleShowResponse.builder()
                .articleId(article.getId())
                .authorId(author.getUserId())
                .authorName(author.getName())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .comments(commentListResponses)
                .commentCount(commentListResponses.size())
                .build();
    }
}
