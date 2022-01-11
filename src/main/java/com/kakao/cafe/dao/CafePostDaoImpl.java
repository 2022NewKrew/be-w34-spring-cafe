package com.kakao.cafe.dao;

import com.kakao.cafe.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CafePostDaoImpl implements CafePostDao {
    @Override
    public void writePost(Post newPost) {

    }

    @Override
    public List<Post> getPostList() {
        return null;
    }

    @Override
    public Post getPostContent(String postId) {
        return null;
    }
}
