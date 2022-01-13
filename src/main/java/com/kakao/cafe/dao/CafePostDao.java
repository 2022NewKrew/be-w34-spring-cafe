package com.kakao.cafe.dao;

import com.kakao.cafe.model.Post;

import java.util.List;

public interface CafePostDao {
    void writePost (Post newPost);
    List<Post> getPostList();
    Post getPostContent(int postId);
}
