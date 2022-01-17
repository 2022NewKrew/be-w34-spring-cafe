package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.AddPostDto;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;

import java.util.List;

public interface PostService {
    PostViewDto findPostViewDtoById(Long postId);

    List<SimplePostInfo> getListOfSimplePostInfo(Integer pageNum, Integer pageSize);

    Post addPost(AddPostDto addPostDto, Long writerId);

    void increaseViewNumById(Long postId);

    int countAll();
}
