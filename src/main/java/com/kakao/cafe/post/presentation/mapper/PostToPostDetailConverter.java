package com.kakao.cafe.post.presentation.mapper;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class PostToPostDetailConverter implements Converter<Post, PostDetailDto> {

    @Override
    public PostDetailDto convert(MappingContext<Post, PostDetailDto> context) {
        Post post = context.getSource();
        List<String> commentStrings = post.getComments()
                .stream()
                .map(Comment::toString)
                .collect(toList());

        return new PostDetailDto(post.getId(), post.getTitle(), post.getContent(), post.getWriterName(), commentStrings);
    }
}
