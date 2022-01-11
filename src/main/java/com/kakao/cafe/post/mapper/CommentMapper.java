package com.kakao.cafe.post.mapper;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.presentation.dto.CreateCommentRequest;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CreateCommentRequest commentRequest){
        String defaultName ="아무개씨";
        return new Comment(defaultName, commentRequest.getComment());
    }
}
