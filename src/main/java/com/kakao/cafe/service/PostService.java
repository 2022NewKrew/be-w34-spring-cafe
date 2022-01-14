package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(CreatePostDto postDto) {
        postRepository.save(Post.builder()
                .writer(postDto.getWriter())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build());
    }

    public ShowPostDto findPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        return new ShowPostDto(post);
    }

    public List<ShowPostDto> findAllPost() {
        return postRepository.findAll().stream()
                .map(ShowPostDto::new)
                .collect(Collectors.toList());
    }


}
