package com.kakao.cafe.util.mapper;

import com.kakao.cafe.dao.CommentInsertDto;
import com.kakao.cafe.domain.*;
import com.kakao.cafe.dto.CommentDbDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDbMapper {
    public static CommentInsertDto toCommentInsertDto(Id questionId, UserId userId, Comment comment) {
        return new CommentInsertDto(
                questionId.getId(),
                userId.getUserId(),
                comment.getWriteTime().getWriteTime(),
                comment.getContents().getContents()
        );
    }

    public static Comment toComment(CommentDbDto commentDbDto) {
        return new Comment(
                new Id(commentDbDto.getCommentId()),
                new Name(commentDbDto.getName()),
                new WriteTime(commentDbDto.getTime()),
                new Contents(commentDbDto.getContents())
        );
    }

    public static Comments toComments(List<CommentDbDto> commentDbDtos) {
        return new Comments(commentDbDtos.stream()
                .map(CommentDbMapper::toComment)
                .collect(Collectors.toList()));
    }
}
