package com.kakao.cafe.repository;

import com.kakao.cafe.dao.PostDao;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

    private final PostDao postDao;

    @Autowired
    public PostRepository(PostDao postDao) {
        this.postDao = postDao;
    }

    public int insert(Post post) {
        return postDao.insert(post);
    }


    public void deleteAll() {
        postDao.deleteAll();
    }

    public Post findById(long id) {
        return postDao.findById(id);
    }

    public Posts findAll() {
        return postDao.findAll();
    }

    public int update(Post post) {
        return postDao.update(post);
    }

    public int delete(long id) {
        return postDao.delete(id);
    }
}
