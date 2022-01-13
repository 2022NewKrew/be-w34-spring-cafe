package com.kakao.cafe.repository;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.util.mapper.PostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    private final PostRepository postRepository;

    @Autowired
    public PostRepositoryTest(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @BeforeEach
    void setUp() {
        postRepository.deleteAll();
    }

    @Test
    void insertAndFindTest() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);
        int id = postRepository.insert(post);
        Post post2 = postRepository.findById(id).get();
        assertThat(post.getWriter()).isEqualTo(post2.getWriter());
        assertThat(post.getTitle()).isEqualTo(post2.getTitle());
        assertThat(post.getWriter()).isEqualTo(post2.getWriter());
    }

    @Test
    void findNullTest() {
        assertThat(postRepository.findById(-1).isEmpty()).isTrue();
    }

    @Test
    void findAllTest() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);
        int id = postRepository.insert(post);
        Posts posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    void findAllEmptyTest() {
        Posts posts = postRepository.findAll();
        assertThat(posts.size()).isEqualTo(0);
    }

}
