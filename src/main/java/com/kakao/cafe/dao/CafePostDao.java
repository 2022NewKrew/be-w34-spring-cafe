package com.kakao.cafe.dao;

import com.kakao.cafe.model.Post;

import java.util.List;

public interface CafePostDao {
    boolean writePost (Post newPost);
    List<Post> getPostList();
    Post getPostContent(int postId);

    Post postViewEdit(int postId);
    boolean editPost(int postId, Post post);
}
