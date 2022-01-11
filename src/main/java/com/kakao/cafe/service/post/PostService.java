package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.model.post.PostWriteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public void writePost(PostWriteRequest post) {
        postRepository.save(Post.builder()
                .writer(post.getWriter())
                .title(post.getTitle())
                .content(post.getContent())
                .build());
    }

}
