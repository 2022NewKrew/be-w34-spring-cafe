package com.kakao.cafe.dao;

import com.kakao.cafe.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CafePostDaoImpl implements CafePostDao {

    List<Post> postList;

    public CafePostDaoImpl() {
        postList = new ArrayList<>();
    }

    @Override
    public void writePost(Post newPost) {
        newPost.setPostId(postList.size() + "");
        postList.add(newPost);
    }

    @Override
    public List<Post> getPostList() {
        return postList;
    }

    @Override
    public Post getPostContent(String postId) {
        if(postId == null) {
            return null;
        }

        Post selectedPost = null;
        for(Post post: postList) {
            if(postId.equals(post.getPostId())) {
                selectedPost = post;
                break;
            }
        }
        return selectedPost;
    }
}
