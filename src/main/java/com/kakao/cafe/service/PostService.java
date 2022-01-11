package com.kakao.cafe.service;

import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    public Post write(PostDto postDto) {
        User writer = userRepository.findByName(postDto.getWriter())
                .orElseThrow(() -> new IllegalArgumentException("닉네임과 일치하는 회원정보가 없습니다."));
        return postRepository.save(Post.from(postDto, writer));
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }
}
