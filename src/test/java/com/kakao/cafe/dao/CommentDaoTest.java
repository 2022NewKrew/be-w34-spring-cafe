package com.kakao.cafe.dao;

import com.kakao.cafe.dto.CommentDbDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CommentDaoTest {

    private final CommentDao commentDao;

    @Autowired
    public CommentDaoTest(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Test
    @Transactional
    void find_all() {
        assertThat(commentDao.findAll(1).size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void insert_test() {
        CommentDbDto commentDbDto = new CommentDbDto(3, 1, "sanjigi", "test");
        assertThat(commentDao.insert(commentDbDto)).isEqualTo(1);
    }

    @Test
    @Transactional
    void update_test() {
        CommentDbDto commentDbDto = new CommentDbDto(1, 1, "sanjigi", "comment1_1");
        assertThat(commentDao.update(commentDbDto)).isEqualTo(1);
        CommentDbDto commentDbDto1 = commentDao.findById(commentDbDto.getId());
        assertFalse(
                (commentDbDto.getId() == commentDbDto1.getId()) &&
                        (commentDbDto.getPostId() == commentDbDto1.getPostId()) &&
                        (commentDbDto.getUserId() == commentDbDto1.getUserId()) &&
                        (commentDbDto.getText().equals(commentDbDto1.getText()))
        );

    }

    @Test
    @Transactional
    void delete_test() {
        assertThat(commentDao.deleteById(1)).isEqualTo(1);
        assertThatThrownBy(() -> commentDao.findById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }


}
