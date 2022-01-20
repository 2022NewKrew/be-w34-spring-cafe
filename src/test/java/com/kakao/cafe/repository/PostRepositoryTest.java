package com.kakao.cafe.repository;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.util.mapper.PostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
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
        Post post2 = postRepository.findById(id);
        assertThat(post.getWriter()).isEqualTo(post2.getWriter());
        assertThat(post.getTitle()).isEqualTo(post2.getTitle());
        assertThat(post.getWriter()).isEqualTo(post2.getWriter());
    }

    @Test
    void findNullTest() {
        assertThatThrownBy(() -> postRepository.findById(-1))
                .isInstanceOf(EmptyResultDataAccessException.class);
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

    @Test
    void updateSuccessTest() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);
        postDto.setId(postRepository.insert(post));
        postDto.setTitle("hello2");
        postDto.setContents("world2");
        assertThat(postRepository.update(PostMapper.toPost(postDto))).isEqualTo(1);
    }

    @Test
    void updateFailTest() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);
        postDto.setId(-2);
        postDto.setTitle("hello2");
        postDto.setContents("world2");
        assertThat(postRepository.update(PostMapper.toPost(postDto))).isEqualTo(0);
    }


    @Test
    @Transactional
    void deleteSuccess() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);
        int id = postRepository.insert(post);
        assertEquals(postRepository.delete(id), 1);

    }

    @Test
    @Transactional
    void deleteFail() {
        assertEquals(postRepository.delete(-1), 0);
    }


}
