package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.PostRepository;
import com.kakao.cafe.model.Article.Post;
import com.kakao.cafe.model.Article.PostCreateRequestDto;
import com.kakao.cafe.model.Article.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void createQuestion(PostCreateRequestDto post) {
        postRepository.add(post.toPost());
    }

    public List<PostResponseDto> getPostList() {
        return postRepository.getPostList().stream()
                .map(Post::toResponseDto)
                .collect(Collectors.toList());
    }

}
