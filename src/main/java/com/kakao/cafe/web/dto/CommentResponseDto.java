package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Comment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentResponseDto {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final Long id;
    private final String commenter;
    private final String contents;
    private final String createdAt;

    private CommentResponseDto(Long id, String commenter, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.commenter = commenter;
        this.contents = contents;
        this.createdAt = createdAt.format(FORMATTER);
    }

    public static List<CommentResponseDto> from(List<Comment> commentList) {
        List<CommentResponseDto> result = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentResponseDto responseDto = new CommentResponseDto(comment.getId(), comment.getCommenter(), comment.getContents(), comment.getCreatedAt());
            result.add(responseDto);
        }
        return result;
    }
}
