package com.kakao.cafe.service;

import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;

import java.util.List;

public interface PostService {
    PostViewDto findPostViewDtoById(Long postId);

    List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize);

    int countAll();
}
