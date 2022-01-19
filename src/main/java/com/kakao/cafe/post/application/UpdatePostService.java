package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePostService {
    private final PostRepository postRepository;

    public void update(Long postId, String newContent){
        postRepository.update(postId, newContent);
    }
}
