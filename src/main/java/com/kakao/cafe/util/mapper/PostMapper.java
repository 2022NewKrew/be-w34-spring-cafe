package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.PostDto;

public class PostMapper {
    public static PostDto toDto(Post post) {
        return new PostDto(post.getWriter(),
                post.getTitle(),
                post.getContents(),
                post.getId());
    }

    public static Post toPost(PostDto postDto) {
        return new Post.Builder()
                .title(postDto.getTitle())
                .writer(postDto.getWriter())
                .contents(postDto.getContents())
                .id(postDto.getId())
                .build();
    }
}
