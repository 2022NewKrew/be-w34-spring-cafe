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
        return new Post(postDto.getTitle(), postDto.getWriter(), postDto.getContents());
    }
}
