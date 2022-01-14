package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.util.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public int insert(Post post) {
        return postRepository.insert(post);
    }

    public Post findById(int postId) throws PostNotFoundException {
        try {
            return postRepository.findById(postId);
        } catch (Exception e) {
            throw new PostNotFoundException(e, postId);
        }
    }

    public Posts findAll() {
        return postRepository.findAll();
    }
}
