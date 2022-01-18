package com.kakao.cafe.service;

import com.kakao.cafe.dao.CafePostDao;
import com.kakao.cafe.helper.PostHelper;
import com.kakao.cafe.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafePostServiceImpl implements CafePostService {

    private final CafePostDao cafePostDao;

    public CafePostServiceImpl(CafePostDao cafePostDao) {
        this.cafePostDao = cafePostDao;
    }

    @Override
    public boolean writePost(Post newPost) {
        if(PostHelper.checkRegexOfPost(newPost)) {
            return cafePostDao.writePost(newPost);
        }
        return false;
    }

    @Override
    public List<Post> getPostList() {
        return cafePostDao.getPostList();
    }

    @Override
    public Post getPostContent(int postId) {
        return cafePostDao.getPostContent(postId);
    }

    @Override
    public Post postViewEdit(int postId) {
        return cafePostDao.postViewEdit(postId);
    }

    @Override
    public boolean editPost(String loginUser, int postId, Post post) {
        if(loginUser == null || !loginUser.equals(post.getUserId())) {
            return false;
        }
        if(PostHelper.checkRegexOfPost(post)) {
            return cafePostDao.editPost(postId, post);
        }
        return false;
    }
}
