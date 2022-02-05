package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Contents;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.dto.CommentFormDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {
    public static Comment toComment(CommentFormDto commentFormDto) {
        return new Comment(new Contents(commentFormDto.getComment()));
    }

    public static CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getCommentId().getId(),
                comment.getName().getName(),
                comment.getWriteTime().getWriteTime(),
                comment.getContents().getContents()
        );
    }
}
