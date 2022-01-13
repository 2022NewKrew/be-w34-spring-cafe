package com.kakao.cafe.service;

import com.kakao.cafe.model.Post;

import java.util.List;

public interface CafePostService {
    boolean writePost (Post newPost);
    List<Post> getPostList();
    Post getPostContent(int postId);
}
