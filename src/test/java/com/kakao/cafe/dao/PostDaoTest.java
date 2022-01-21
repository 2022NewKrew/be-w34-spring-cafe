package com.kakao.cafe.dao;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.PostDto;
import com.kakao.cafe.util.mapper.PostMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PostDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDaoTest.class);
    @Autowired
    PostDao postDao;

    @Test
    @Transactional
    void insertAndFind() {
        PostDto postDto = new PostDto("writer1", "hello", "world");
        Post post = PostMapper.toPost(postDto);

        int id = postDao.insert(post);
        Post newPost = postDao.findById(id);
        assertThat(id == newPost.getId()).isTrue();
    }

    @Test
    @Transactional
    void findEmptyResult() {
        assertThatThrownBy(() -> postDao.findById(100))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }


    @Test
    @Transactional
    void updateSucessTest() {
        PostDto postDto = new PostDto("javajigi", "JAVAJIGI2", "hello world2", 1);
        Post post = PostMapper.toPost(postDto);
        System.out.println("post.getId() : " + post.getId());
        postDao.update(post);
        Post newInfo = postDao.findById(post.getId());
        assertEquals(post.getTitle(), newInfo.getTitle());
        assertEquals(post.getContents(), newInfo.getContents());
    }

    @Test
    @Transactional
    void deleteSuccessTest() {
        assertEquals(postDao.delete(1), 1);
    }

    @Test
    @Transactional
    void deleteFailTest() {
        assertEquals(postDao.delete(-1), 0);
    }


}
