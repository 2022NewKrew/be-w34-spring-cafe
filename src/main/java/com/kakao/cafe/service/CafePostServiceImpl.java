package com.kakao.cafe.service;

import com.kakao.cafe.dao.CafePostDao;
import com.kakao.cafe.dao.CafePostDaoImpl;
import com.kakao.cafe.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafePostServiceImpl implements CafePostService {

    @Autowired
    CafePostDao cafePostDao = new CafePostDaoImpl();

    @Override
    public void writePost(Post newPost) {
        cafePostDao.writePost(newPost);
    }

    @Override
    public List<Post> getPostList() {
        return cafePostDao.getPostList();
    }

    @Override
    public Post getPostContent(String postId) {
        return cafePostDao.getPostContent(postId);
    }
}
