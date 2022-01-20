package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.comment.Comment;
import com.kakao.cafe.dto.CommentWebDto;

public class CommentWebMapper {

    public static CommentWebDto toDto(Comment comment) {
        return new CommentWebDto(comment.getUser().getName(),
                comment.getUser().getId(),
                comment.getText(),
                comment.getId());
    }

}
