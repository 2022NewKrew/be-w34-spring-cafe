package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public void writePost(PostWriteRequest post) {
        postRepository.save(Post.builder()
                .writer(post.getWriter())
                .title(post.getTitle())
                .content(post.getContent())
                .build());
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

}
