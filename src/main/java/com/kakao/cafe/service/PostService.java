package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostListDto;

import java.util.List;

public interface PostService {
    void create(PostCreateDto postCreateDto);
    List<PostListDto> getList();
}
