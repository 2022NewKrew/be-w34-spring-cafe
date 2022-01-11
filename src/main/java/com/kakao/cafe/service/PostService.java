package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostListItemDto;

import java.util.List;

public interface PostService {
    void create(PostCreateDto postCreateDto);
    List<PostListItemDto> getList();
}
