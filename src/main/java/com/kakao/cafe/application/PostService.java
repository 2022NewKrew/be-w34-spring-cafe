package com.kakao.cafe.application;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.interfaces.common.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void write(PostDto postDto) {
        postRepository.add(postMapper.toEntity(postDto));
    }
}
