package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Optional<Post> findPost(Long id){
        return postRepository.findById(id);
    }

    public List<Post> findAllPost(){
        return postRepository.findAll();
    }


}
