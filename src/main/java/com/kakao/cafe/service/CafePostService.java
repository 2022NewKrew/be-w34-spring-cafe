package com.kakao.cafe.service;

import com.kakao.cafe.model.Post;

import java.util.List;

public interface CafePostService {
    boolean writePost (Post newPost);
    List<Post> getPostList();
    Post getPostContent(int postId);

    Post postViewEdit(int postId);
    boolean editPost(String loginUser, int postId, Post post);
    boolean deletePost(int postId, String userId);
}
