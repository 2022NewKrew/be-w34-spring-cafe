package com.kakao.cafe.post.presentation.mapper;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.presentation.dto.CommentRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class CommentRequestToCommentConverter implements Converter<CommentRequest, Comment> {
    @Override
    public Comment convert(MappingContext<CommentRequest, Comment> context) {
        CommentRequest commentRequest = context.getSource();
        String defaultName ="아무개씨";
        return new Comment(defaultName, commentRequest.getComment());
    }
}
