package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Comment;
import com.kakao.cafe.user.domain.User;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SingleComment {

    private Long commentId;
    private String body;
    private String createdAt;
    private Long authorId;
    private String authorName;

    public static SingleComment of(Comment comment, User author) {
        return SingleComment.builder()
            .commentId(comment.getId())
            .body(comment.getBody())
            .createdAt(
                comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
            .authorId(comment.getAuthorId())
            .authorName(author.getNickname())
            .build();
    }
}
