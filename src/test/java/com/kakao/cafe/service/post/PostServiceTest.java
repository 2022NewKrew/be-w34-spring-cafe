package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    private final int size = 20;

    @BeforeEach
    void setup() {
        for (int i = 0; i < size; i++) {
            Post post = Post.builder()
                    .writer("작성자")
                    .title("게시글 제목" + i)
                    .content("게시글 내용 " + i)
                    .createdAt(LocalDateTime.now())
                    .build();
            postRepository.save(post);
        }
    }

    @AfterEach
    void cleanup() {
        postRepository.deleteAll();
    }

    @DisplayName("정상적으로 게시글을 저장할 때, 에러가 발생하지 않아야 한다.")
    @Test
    void writePost() {
        String writer = "작성자";
        String title = "게시글 제목";
        String content = "게시글 내용";
        PostWriteRequest post = PostWriteRequest.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        assertThatNoException().isThrownBy(() -> {
            postService.writePost(post);
        });
    }

    @DisplayName("게시글을 저장하면, 작성일자가 null이 아니여야 한다.")
    @Test
    void writePostForCheckingCreatedAt() {
        String writer = "작성자";
        String title = "게시글 제목";
        String content = "게시글 내용";
        PostWriteRequest post = PostWriteRequest.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .build();

        postService.writePost(post);
        List<Post> posts = postRepository.findAll();
        posts.forEach(p -> assertThat(p.getCreatedAt()).isNotNull());
    }

    @DisplayName("등록된 게시글의 수와 조회한 게시글의 총 개수는 같아야 한다.")
    @Test
    void getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();

        assertThat(posts.size()).isEqualTo(size);
    }

    @DisplayName("등록된 게시글의 id를 통해서 게시글의 정보를 조회할 때, 에러가 발생하지 않아야 한다.")
    @Test
    void getPostById() {
        long id = 11;

        assertThatNoException().isThrownBy(() -> {
            PostDto post = postService.getPostById(id);
            assertThat(post).isNotNull();
        });
    }

    @DisplayName("등록되지 않은 게시글의 id를 통해서 게시글의 정보를 조회할 때, 에러가 발생해야 한다.")
    @Test
    void getPostByIdNotContained() {
        long id = size + 1;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            PostDto post = postService.getPostById(id);
        });
    }

}
