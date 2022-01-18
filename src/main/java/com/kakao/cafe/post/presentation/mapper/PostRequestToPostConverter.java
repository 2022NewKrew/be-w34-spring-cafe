package com.kakao.cafe.post.presentation.mapper;

import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.PostRequest;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PostRequestToPostConverter implements Converter<PostRequest, Post> {
    @Override
    public Post convert(MappingContext<PostRequest, Post> context) {
        PostRequest postRequest = context.getSource();
        return new Post(postRequest.getTitle(), postRequest.getContent(), postRequest.getWriterName());
    }
}
