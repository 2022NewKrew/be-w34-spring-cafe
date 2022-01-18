package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.dto.PostListItemDto;
import com.kakao.cafe.dto.PostUpdateDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface PostService {
    void create(PostCreateDto postCreateDto, HttpSession session);
    List<PostListItemDto> getList();
    PostDetailDto get(int questionId);
    void delete(int questionId, HttpSession session);
    PostDetailDto getUpdate(int questionId, HttpSession session);
    void update(int questionId, PostUpdateDto postUpdateDto, HttpSession session);
}
