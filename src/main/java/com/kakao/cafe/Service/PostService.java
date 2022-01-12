package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.PostRepository;
import com.kakao.cafe.model.Article.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public final void createQuestion(PostCreateRequestDto post) {
        postRepository.add(post.toPost());
    }

}
