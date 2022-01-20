package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
    private Post post;
    private User writer;

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
        this.writer = userRepository.findById(writerId).orElse(null);
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
        log.info("posts : {}", posts);
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
        assertThat(foundPost.getWriterNickname()).isEqualTo(writer.getNickname());
    }

    @DisplayName("게시글 수정 - 정상 테스트")
    @Test
    void update() {
        long id = post.getId();
        String title = "게시글 제목 수정";
        String content = "게시글 내용 수정 합니다.";
        LocalDateTime updatedAt = LocalDateTime.now();
        Post postForUpdate = Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .updatedAt(updatedAt)
                .build();

        assertThatNoException().isThrownBy(() -> {
            postRepository.update(postForUpdate);

            Post updatedPost = postRepository.findById(id).orElse(null);
            assertThat(updatedPost).isNotNull();
            assertThat(updatedPost.getId()).isEqualTo(id);
            assertThat(updatedPost.getTitle()).isEqualTo(title);
            assertThat(updatedPost.getContent()).isEqualTo(content);
            assertThat(updatedPost.getUpdatedAt()).isEqualTo(updatedAt);
            assertThat(updatedPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
            assertThat(updatedPost.getWriterId()).isEqualTo(post.getWriterId());
            assertThat(updatedPost.getWriterNickname()).isEqualTo(post.getWriterNickname());
        });
    }

    @DisplayName("게시글 삭제 - 정상 테스트")
    @Test
    void deleteById() {
        long id = post.getId();

        assertThatNoException().isThrownBy(() -> {
            postRepository.deleteById(id);

            Post deletedPost = postRepository.findById(id).orElse(null);
            assertThat(deletedPost).isNull();
        });
    }

}
