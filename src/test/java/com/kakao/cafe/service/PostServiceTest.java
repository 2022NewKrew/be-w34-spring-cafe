package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.MemoryPostRepository;
import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {
    PostService postService;
    MemoryPostRepository postRepository;

    @BeforeEach
    void setUp(){
        postRepository = new MemoryPostRepository();
        postService = new PostService(postRepository);
    }

    @AfterEach
    void tearDown(){
        postRepository.clear();
    }

    @Test
    @DisplayName("게시글 전체 조회")
    void showAllPost(){
        int size = 5;
        List<CreatePostDto> createPostList = IntStream.range(1, size).boxed()
                .map(num -> postBuilder("test" + num, "title" + num, "content" + num))
                .collect(Collectors.toList());

        createPostList.forEach(post -> postService.createPost(post));

        List<ShowPostDto> allPost = postService.findAllPost();
        assertThat(createPostList.size()).isEqualTo(allPost.size());
    }

    @Test
    @DisplayName("게시글 등록 성공")
    void createPost(){
        CreatePostDto postDto = postBuilder("test", "title", "content");

        postService.createPost(postDto);
        ShowPostDto getPost = postService.findPost(1L);

        assertThat(postDto.getWriter()).isEqualTo(getPost.getWriter());
        assertThat(postDto.getTitle()).isEqualTo(getPost.getTitle());
        assertThat(postDto.getContent()).isEqualTo(getPost.getContent());
    }

    private CreatePostDto postBuilder(String writer, String title, String content){
        return new CreatePostDto(writer, title, content);
    }

}
