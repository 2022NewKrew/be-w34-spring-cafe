package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.util.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<Post> res = postRepository.findById(postId);
        if (res.isEmpty())
            throw new PostNotFoundException(postId);
        return res.get();
    }

    public Posts findAll() {
        return postRepository.findAll();
    }
}
