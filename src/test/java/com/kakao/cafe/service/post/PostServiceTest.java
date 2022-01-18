package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.exception.NoAuthorizationException;
import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@Slf4j
@SpringBootTest
class PostServiceTest {
    private static final int INIT_SIZE_OF_POSTS = 3; // 데이터베이스에 저장된 초기 게시글의 수

    @Autowired
    private PostService postService;

    @Qualifier("postJdbcRepositoryImpl")
    @Autowired
    private PostRepository postRepository;
    private final int size = 20;
    private final long writerId = 1L;

    @BeforeEach
    void setup() {
        for (int i = 0; i < size; i++) {
            Post post = Post.builder()
                    .writerId(writerId)
                    .title("게시글 제목" + i)
                    .content("게시글 내용 " + i)
                    .createdAt(LocalDateTime.now())
                    .build();
            postRepository.save(post);
        }
    }

    @DisplayName("정상적으로 게시글을 저장할 때, 에러가 발생하지 않아야 한다.")
    @Test
    void writePost() {
        String title = "게시글 제목";
        String content = "게시글 내용";
        PostWriteRequest post = PostWriteRequest.builder()
                .title(title)
                .content(content)
                .build();
        long writerId = 1L;

        assertThatNoException().isThrownBy(() -> {
            postService.writePost(post, writerId);
        });
    }

    @DisplayName("게시글을 저장하면, 작성일자가 null이 아니여야 한다.")
    @Test
    void writePostForCheckingCreatedAt() {
        String title = "게시글 제목";
        String content = "게시글 내용";
        PostWriteRequest post = PostWriteRequest.builder()
                .title(title)
                .content(content)
                .build();
        long writerId = 1L;

        postService.writePost(post, writerId);
        List<Post> posts = postRepository.findAll();
        posts.forEach(p -> assertThat(p.getCreatedAt()).isNotNull());
    }

    @DisplayName("등록된 게시글의 수와 조회한 게시글의 총 개수는 같아야 한다.")
    @Test
    void getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();

        assertThat(posts.size()).isEqualTo(INIT_SIZE_OF_POSTS + size);
    }

    @DisplayName("등록된 게시글의 id를 통해서 게시글의 정보를 조회할 때, 에러가 발생하지 않아야 한다.")
    @Test
    void getPostById() {
        long id = 1;

        assertThatNoException().isThrownBy(() -> {
            PostDto post = postService.getPostById(id);
            assertThat(post).isNotNull();
        });
    }

    @DisplayName("등록되지 않은 게시글의 id를 통해서 게시글의 정보를 조회할 때, 에러가 발생해야 한다.")
    @Test
    void getPostByIdNotContained() {
        Post lastPost = postRepository.findAll().stream().max((p1, p2) -> (int) (p1.getId() - p2.getId())).orElse(null);
        assertThat(lastPost).isNotNull();
        long id = lastPost.getId() + 1;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            PostDto post = postService.getPostById(id);
        });
    }

    @DisplayName("게시글 id와 작성자 id를 이용하여 게시글을 조회할 때, 작성자 id가 일치하면 에러가 발생하지 않는다.")
    @Test
    void getPostById_MatchWriterId() {
        Post lastPost = postRepository.findAll().stream().max((p1, p2) -> (int) (p1.getId() - p2.getId())).orElse(null);
        assertThat(lastPost).isNotNull();
        long id = lastPost.getId();

        assertThatNoException().isThrownBy(() -> {
            PostDto post = postService.getPostById(id, writerId);
            assertThat(post).isNotNull();
            assertThat(post.getWriterId()).isEqualTo(writerId);
        });
    }

    @DisplayName("게시글 id와 작성자 id를 이용하여 게시글을 조회할 때, 작성자 id가 일치하지 않으면 에러가 발생해야 한다.")
    @Test
    void getPostById_NotMatchWriterId() {
        Post lastPost = postRepository.findAll().stream().max((p1, p2) -> (int) (p1.getId() - p2.getId())).orElse(null);
        assertThat(lastPost).isNotNull();
        long id = lastPost.getId();
        long writerId = 2L;

        assertThatExceptionOfType(NoAuthorizationException.class).isThrownBy(() -> {
            PostDto post = postService.getPostById(id, writerId);
        });
    }

}
