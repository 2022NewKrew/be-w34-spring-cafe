package com.kakao.cafe.application;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.exception.NoSuchPostException;
import com.kakao.cafe.interfaces.common.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(NoSuchPostException::new);
    }

    public void write(PostDto postDto) {
        postRepository.save(postMapper.toEntity(postDto));
    }

    public void deleteById(long id) {
        postRepository.deleteById(id);
    }
}
