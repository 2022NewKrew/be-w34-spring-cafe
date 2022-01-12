package com.kakao.cafe.Service;

import com.kakao.cafe.Repository.PostRepository;
import com.kakao.cafe.model.Post.Post;
import com.kakao.cafe.model.Post.PostCreateRequestDto;
import com.kakao.cafe.model.Post.PostResponseDto;
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

    public PostResponseDto findPostById(Long id) {
        return postRepository.getPostList().stream()
                .filter(p -> id.equals(p.getId()))
                .collect(Collectors.toList())
                .get(0)
                .toResponseDto();
    }
}
