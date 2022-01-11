package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostListItemDto;
import com.kakao.cafe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void create(PostCreateDto postCreateDto) {
        Post post = Post.of(postCreateDto);
        postRepository.save(post);
    }

    @Override
    public List<PostListItemDto> getList() {
        Optional<List<Post>> postList = postRepository.findAll();

        return postList.map(posts -> posts
                .stream()
                .map(PostListItemDto::of)
                .collect(Collectors.toList())).orElse(null);
    }
}
