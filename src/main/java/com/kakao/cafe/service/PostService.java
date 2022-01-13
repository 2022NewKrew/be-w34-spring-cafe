package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.PostRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void writePost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findByPostId(id);
    }

}
