package com.kakao.cafe.domain.post;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@Slf4j
@SpringBootTest
class PostJdbcRepositoryImplTest {
    private static final int INIT_SIZE_OF_POSTS = 3; // 데이터베이스에 저장된 초기 게시글의 수

    @Qualifier("postJdbcRepositoryImpl")
    @Autowired
    private PostRepository postRepository;
    private Post post;

    @BeforeEach
    void setup() {
        long writerId = 1;
        String title = "게시글 제목";
        String content = "게시글 내용입니다.";
        Post post = Post.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
        this.post = postRepository.findAll().stream().findFirst().orElse(null);
    }

    @DisplayName("정상적인 게시글이라면 저장할 때 에러가 발생하지 않아야 한다.")
    @Test
    void save() {
        long writerId = 1;
        String title = "게시글 제목";
        String content = "게시글 내용입니다.";
        Post post = Post.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        assertThatNoException().isThrownBy(() -> {
            postRepository.save(post);
        });
    }

    @DisplayName("게시글의 작성자가 등록된 회원이 아니라면 에러가 발생해야 한다.")
    @Test
    void saveWhenTitleIsNull() {
        long writerId = 5;
        String title = "게시글 제목";
        String content = "게시글 내용입니다.";
        Post post = Post.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            postRepository.save(post);
        });
    }

    @DisplayName("게시글의 제목이 null 이라면 에러가 발생해야 한다.")
    @Test
    void saveWhenWriterIsNull() {
        long writerId = 1;
        String title = null;
        String content = "게시글 내용입니다.";
        Post post = Post.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            postRepository.save(post);
        });
    }

    @DisplayName("게시글의 내용이 null 이라면 에러가 발생해야 한다.")
    @Test
    void saveWhenContentIsNull() {
        long writerId = 1;
        String title = "게시글 제목";
        String content = null;
        Post post = Post.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> {
            postRepository.save(post);
        });
    }

    @DisplayName("조회된 게시글 목록의 크기는 저장된 게시글의 개수와 같아야 한다.")
    @Test
    void findAll() {
        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(INIT_SIZE_OF_POSTS + 1);
    }

    @DisplayName("조회된 게시글의 정보는 등록된 게시글의 정보와 같아야 한다.")
    @Test
    void findById() {
        long id = post.getId();

        Post foundPost = postRepository.findById(id).orElse(null);

        assertThat(foundPost).isNotNull();
        assertThat(foundPost.getWriterId()).isEqualTo(post.getWriterId());
        assertThat(foundPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(foundPost.getContent()).isEqualTo(post.getContent());
        assertThat(foundPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
    }

    @DisplayName("게시글의 목록을 전체 삭제하면, 게시글의 목록의 크기는 0이 되어야 한다.")
    @Test
    void deleteAll() {
        postRepository.deleteAll();
        List<Post> posts = postRepository.findAll();

        assertThat(posts.isEmpty()).isTrue();
    }
}
