package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.PostCreateDto;
import com.kakao.cafe.dto.PostDetailDto;
import com.kakao.cafe.dto.PostListItemDto;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(PostCreateDto postCreateDto) {
        User user = userRepository.findByUserId(postCreateDto.getWriter());
        if (user == null)
            throw new IllegalArgumentException("user not found");

        Post post = Post.of(postCreateDto, user);
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

    @Override
    public PostDetailDto get(int questionId) {
        Post post = postRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("post not found"));
        post.updateViewCount();
        return PostDetailDto.of(post);
    }
}
