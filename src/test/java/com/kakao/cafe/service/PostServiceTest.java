package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.util.exception.PostNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class PostServiceTest {

    private final PostService postService;

    @Autowired
    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }

    @BeforeEach
    void setUp() {
        postService.deleteAll();
    }

    @Test
    void insertAndGet() {
        Post post = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        int postId = postService.insert(post);
        Post post2 = postService.findById(postId);
        assertThat(
                (postId == post2.getId()) &&
                        (post.getWriter().equals(post2.getWriter())) &&
                        (post.getTitle().equals(post2.getTitle())) &&
                        (post.getContents().equals(post2.getContents()))
        ).isTrue();
    }

    @Test
    void findAllTest() {
        Post post = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        Post post2 = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        postService.insert(post);
        postService.insert(post2);
        assertThat(postService.findAll().size()).isEqualTo(2);
    }

    @Test
    void postNotFoundTest() {
        Post post = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        postService.insert(post);
        assertThatThrownBy(() -> postService.findById(-1))
                .isInstanceOf(PostNotFoundException.class);

    }


    public void deleteAll() {

    }
}
