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
        String defaultName = "아무개"; // session 적용 전 잠시

        return new Post(postRequest.getTitle(), postRequest.getContent(), defaultName);
    }
}
