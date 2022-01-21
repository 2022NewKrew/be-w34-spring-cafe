package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.util.exception.wrappable.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Transactional
@SpringBootTest
public class PostWriteServiceTest {

    private final PostWriteService postWriteService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostWriteServiceTest(PostWriteService postWriteService, UserService userService, PostService postService) {
        this.postWriteService = postWriteService;
        this.userService = userService;
        this.postService = postService;
    }

    @BeforeEach
    void setUp() {
        userService.deleteAll();
        postService.deleteAll();
    }

    @Test
    void canUserWriteFailTest() {
        User user = new User.Builder()
                .id("writer1")
                .password("1q2w3e4r")
                .name("name1")
                .email("writer@kakao.com")
                .build();

        userService.insert(user);

        Post post = new Post.Builder()
                .writer("writer2")
                .title("hello")
                .contents("world")
                .build();
        assertThatThrownBy(() -> postWriteService.insertPost(post)).isInstanceOf(UserNotFoundException.class);

    }

}
