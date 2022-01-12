package com.kakao.cafe.service.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.model.post.PostDto;
import com.kakao.cafe.model.post.PostWriteRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

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

    @DisplayName("등록된 게시글의 수와 조회한 게시글의 총 개수는 같아야 한다.")
    @Test
    void getAllPosts() {
        int size = 31;
        for (int i = 0; i < size; i++) {
            Post post = Post.builder()
                    .writer("작성자")
                    .title("게시글 제목" + i)
                    .content("게시글 내용 " + i + i)
                    .build();
            postRepository.save(post);
        }

        List<PostDto> posts = postService.getAllPosts();

        assertThat(posts.size()).isEqualTo(size);
    }
}
