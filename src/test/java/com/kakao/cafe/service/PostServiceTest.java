package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.util.exception.PostNotFoundException;
import com.kakao.cafe.util.exception.UnauthorizedAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class PostServiceTest {

    private final PostService postService;

    @Autowired
    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }


    @Test
    @Transactional
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
    @Transactional
    void findAllTest() {
        postService.deleteAll();
        Post post = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        Post post2 = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        postService.insert(post);
        postService.insert(post2);
        assertThat(postService.findAll().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void postNotFoundTest() {
        Post post = new Post.Builder().writer("yunyul").title("hello").contents("world").build();
        postService.insert(post);
        assertThatThrownBy(() -> postService.findById(-1))
                .isInstanceOf(PostNotFoundException.class);

    }

    @Test
    @Transactional
    void updateNotMineFail() {

        Post post = new Post.Builder()
                .writer("javajigi")
                .title("wrong")
                .contents("post")
                .id(1)
                .build();

        String curUserId = "wrongGuy";

        assertThatThrownBy(() -> postService.update(post, curUserId))
                .isInstanceOf(UnauthorizedAction.class);
    }

    @Test
    @Transactional
    void updateSuccess() {
        Post post = new Post.Builder()
                .writer("javajigi")
                .title("wrong")
                .contents("post")
                .id(1)
                .build();
        postService.update(post, post.getWriter());
    }

    @Test
    @Transactional
    void deleteFailByPostNotExists() {
        String curId = "javajigi";
        assertThatThrownBy(() -> postService.delete(curId, -1)).isInstanceOf(PostNotFoundException.class);

    }

    @Test
    @Transactional
    void deleteFailByNoLogin() {
        assertThatThrownBy(() -> postService.delete(null, 1)).isInstanceOf(UnauthorizedAction.class);

    }


    @Test
    @Transactional
    void deleteFailByNotWriter() {
        assertThatThrownBy(() -> postService.delete("wrongGuy", 1)).isInstanceOf(UnauthorizedAction.class);

    }

    @Test
    @Transactional
    void deleteSuccess() {
        String curId = "javajigi";
        postService.delete(curId, 1);
    }

}
