package com.kakao.cafe.service;

import com.kakao.cafe.model.Post;

import java.util.List;

public interface CafePostService {
    void writePost (Post newPost);
    List<Post> getPostList();
    Post getPostContent(String postId);
}
