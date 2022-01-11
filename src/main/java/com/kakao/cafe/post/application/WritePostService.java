package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritePostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.savePost(post);
    }
}
