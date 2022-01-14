package com.kakao.cafe.dao;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.util.mapper.PostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoTest.class);
    @Autowired
    PostDao postDao;

    @BeforeEach
    void setUp() {
        postDao.deleteAll();
    }

    @Test
    void insertAndFind() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);

        int id = postDao.insert(post);
        Post newPost = postDao.findById(id);
        assertThat(id == newPost.getId()).isTrue();
    }

    @Test
    void findEmptyResult() {
        assertThatThrownBy(() -> postDao.findById(2))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void findAllTest() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);
        PostDto postDto2 = new PostDto("writer1", "hello2", "myworld");
        Post post2 = PostMapper.toPost(postDto2);
        postDao.insert(post);
        postDao.insert(post2);
        Posts result = postDao.findAll();
        assertEquals(result.size(), 2);
    }

    @Test
    void findAllEmptyTest() {
        assertThat(postDao.findAll().size()).isEqualTo(0);
    }

}
