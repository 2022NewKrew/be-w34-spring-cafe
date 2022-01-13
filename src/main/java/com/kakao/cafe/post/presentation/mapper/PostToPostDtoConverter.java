package com.kakao.cafe.post.presentation.mapper;

import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.presentation.dto.PostDetailDto;
import com.kakao.cafe.post.presentation.dto.PostDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PostToPostDtoConverter implements Converter<Post, PostDto> {
    @Override
    public PostDto convert(MappingContext<Post, PostDto> context) {
        Post post = context.getSource();
        String summary = summarizeContent(post.getContent());

        return new PostDto(post.getId(), post.getTitle(), post.getWriterName(), summary);
    }

    private String summarizeContent(String content){
        return content.length() > PostDetailDto.SUMMARY_SIZE
                ? content.substring(0, PostDetailDto.SUMMARY_SIZE)
                : content;
    }
}
