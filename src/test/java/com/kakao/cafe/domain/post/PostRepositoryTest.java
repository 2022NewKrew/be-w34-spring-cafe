package com.kakao.cafe.domain.post;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@Slf4j
class PostRepositoryTest {
    private final PostRepository postRepository = new PostRepositoryImpl();
    private final List<Post> posts = new ArrayList<>();

    @BeforeEach
    private void setup() {
        setPostsForTest();
    }

    private void setPostsForTest() {
        int sizeOfPosts = 10;
        for (int i = 0; i < sizeOfPosts; i++) {
            Post post = Post.builder()
                    .writer("테스트")
                    .title("게시물 제목" + i)
                    .content("게시물 내용" + i)
                    .build();
            posts.add(post);
            postRepository.save(post);
        }
    }

    @AfterEach
    private void cleanup() {
        posts.clear();
        postRepository.deleteAll();
    }

    @DisplayName("게시글이 정상적으로 등록되면 해당 도메인 객체의 id는 null 이 아니다.")
    @Test
    void save() {
        String writer = "테스터";
        String title = "게시글 제목";
        String content = "게시글 내용";
        Post post = Post.builder()
                .writer(writer)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
        assertThat(post.getId()).isNotNull();
    }

    @DisplayName("게시글이 null 이라면 게시글을 저장할 때, 에러가 발생한다.")
    @Test
    void saveNull() {
        Post post = null;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            postRepository.save(post);
        });
    }

    @DisplayName("게시글 목록에 저장되어 있는 게시글의 개수와 조회한 게시글 목록의 크기는 같아야 한다.")
    @Test
    void findAll() {
        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(this.posts.size());
    }

    @DisplayName("id를 이용하여 게시글을 조회하면 해당 게시글의 정보가 조회되어야 한다.")
    @Test
    void findById() {
        int index = 7;

        Post post = postRepository.findById(index + 1).orElse(null);
        Post expected = posts.get(index);

        assertThat(post).isNotNull();
        assertThat(post.getId()).isEqualTo(expected.getId());
        assertThat(post.getWriter()).isEqualTo(expected.getWriter());
        assertThat(post.getTitle()).isEqualTo(expected.getTitle());
        assertThat(post.getContent()).isEqualTo(expected.getContent());
        assertThat(post.getCreatedAt()).isEqualTo(expected.getCreatedAt());
    }

    @DisplayName("게시글 목록을 전체 삭제하면 저장되어 있는 게시글 목록의 크기는 비어있어야 한다.")
    @Test
    void deleteAll() {
        postRepository.deleteAll();
        List<Post> posts = postRepository.findAll();

        assertThat(posts.isEmpty()).isTrue();
    }
}
