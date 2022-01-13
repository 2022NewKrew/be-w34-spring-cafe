package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.post.PostReqDto;
import com.kakao.cafe.dto.post.PostResDto;
import com.kakao.cafe.repository.post.MemoryPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final MemoryPostRepository postRepository;
    private static long sequence = 0L;

    @Override
    public void addPost(PostReqDto postReqDto) {
        Post post = Post.builder()
                .postId(++sequence)
                .writer(postReqDto.getWriter())
                .title(postReqDto.getTitle())
                .contents(postReqDto.getContents())
                .build();
        postRepository.save(post);
    }

    @Override
    public List<PostResDto> findPosts() {
        return postRepository.findAll().stream()
                .map(PostResDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public PostResDto findPostByPostId(Long postId) {
        return new PostResDto(postRepository.findByPostId(postId)
                .orElseThrow(() -> new NullPointerException("존재하지 않는 게시글입니다.")));
    }
}
