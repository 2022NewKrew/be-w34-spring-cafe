package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.PostRepository;
import com.kakao.cafe.model.Post.PostCreateRequestDto;
import com.kakao.cafe.model.Post.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void createQuestion(PostCreateRequestDto post) {
        postRepository.add(post.toPost());
    }

    public List<PostResponseDto> getPostList() {
        return postRepository.getPostList();
    }

    public PostResponseDto findPostById(Long id) {
        return postRepository.findPostById(id);
    }
}
