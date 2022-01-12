package com.kakao.cafe.service;

import com.kakao.cafe.dto.PageRequestDto;
import com.kakao.cafe.dto.PageResultDto;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.entity.Post;

public interface PostService {
    Long register(PostDto dto);

    PostDto getPost(Long postId);

    PageResultDto<PostDto, Post> getList(PageRequestDto requestDto);

    default Post dtoToEntity(PostDto dto) {
        return Post.builder()
                .postId(dto.getPostId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writerEmail(dto.getWriterEmail())
                .viewCount(dto.getViewCount())
                .build();
    }

    default PostDto entityToDto(Post entity) {
        return PostDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerEmail(entity.getWriterEmail())
                .viewCount(entity.getViewCount())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
