package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.entity.Post;
import com.kakao.cafe.entity.User;

public interface PostService {
    Long register(PostDto dto);

    PostDto read(Long postId);

    PageResultDto<PostDto, Post> getList(PageRequestDto requestDto);

    default Post dtoToEntity(PostDto dto) {
        return Post.builder()
                .postId(dto.getPostId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(User.builder().email(dto.getWriterEmail()).username(dto.getWriterUsername()).build())
                .viewCount(dto.getViewCount())
                .build();
    }

    default PostDto entityToDto(Post entity) {
        return PostDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerEmail(entity.getWriter().getEmail())
                .writerUsername(entity.getWriter().getUsername())
                .viewCount(entity.getViewCount())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
