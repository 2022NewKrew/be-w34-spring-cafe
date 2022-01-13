package com.kakao.cafe.post.application;

import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostInfoService {
    private static final int PAGE_SIZE = 15;
    private final PostRepository postRepository;

    public List<Post> getPosts(int page) {
        List<Post> posts = postRepository.getPosts((page-1) * PAGE_SIZE, PAGE_SIZE);
        System.out.println(posts);
        return posts;
    }

    public Post getPost(Long id) {
        return postRepository.getPost(id).orElseThrow(
                () -> new IllegalArgumentException("해당 Id에 해당하는 게시글이 없습니다."));
    }
}
