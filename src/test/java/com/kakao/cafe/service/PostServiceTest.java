package com.kakao.cafe.service;

import com.kakao.cafe.dto.post.CreatePostDto;
import com.kakao.cafe.dto.post.ShowPostDto;
import com.kakao.cafe.dto.post.UpdatePostDto;
import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.util.exception.NotFoundException;
import org.h2.command.ddl.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    String testUser = "test";

    @BeforeEach
    void setup(){
        CreateUserDto user = CreateUserDto.builder()
                .userId(testUser)
                .password("1234")
                .email("test@test.com")
                .name("test333")
                .build();

        userService.join(user);
    }

    @Test
    @DisplayName("게시글 전체 조회")
    void showAllPost() {
        int size = 5;
        List<CreatePostDto> createPostList = IntStream.range(1, size).boxed()
                .map(num -> new CreatePostDto("title" + num, "content" + num))
                .collect(Collectors.toList());

        createPostList.forEach(post -> postService.createPost(post, testUser));

        List<ShowPostDto> allPost = postService.findAllPost();
        assertThat(createPostList.size()).isEqualTo(allPost.size());
    }

    @Test
    @DisplayName("게시글 등록")
    void createPost() {
        CreatePostDto postDto = new CreatePostDto("title", "content");
        assertThatNoException().isThrownBy(() -> postService.createPost(postDto, testUser));
    }

    @Test
    @DisplayName("게시글 수정")
    void updatePost() {
        CreatePostDto postDto = new CreatePostDto("title", "content");
        ShowPostDto post = postService.createPost(postDto, testUser);

        UpdatePostDto updatePostDto = UpdatePostDto.builder()
                .title("update")
                .content("updateContent")
                .build();
        ShowPostDto updatedPost = postService.updatePost(post.getId(), updatePostDto);

        assertThat(updatedPost.getId()).isEqualTo(post.getId());
        assertThat(updatedPost.getTitle()).isEqualTo(updatePostDto.getTitle());
        assertThat(updatedPost.getContent()).isEqualTo(updatePostDto.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void deletePost() {
        CreatePostDto postDto = new CreatePostDto("title", "content");
        ShowPostDto post = postService.createPost(postDto, testUser);

        postService.deletePost(post.getId(), testUser);

        assertThrows(NotFoundException.class, () -> postService.findPost(post.getId()));
    }

}
