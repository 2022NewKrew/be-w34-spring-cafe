package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
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

}
