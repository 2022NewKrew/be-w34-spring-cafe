package com.kakao.cafe.dao;

import com.kakao.cafe.dto.CommentDbDto;
import com.kakao.cafe.dto.CommentPostUserDbDto;
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
    void findAllTest() {
        assertThat(commentDao.findAll(1).size()).isEqualTo(1);
    }

    @Test
    @Transactional
    void insertSuccess() {
        CommentDbDto commentDbDto = new CommentDbDto(3, 1, "sanjigi", "test");
        assertThat(commentDao.insert(commentDbDto)).isEqualTo(1);
    }

    @Test
    @Transactional
    void updateSuccess() {
        CommentDbDto commentDbDto = new CommentDbDto(1, 1, "sanjigi", "comment1_1");
        assertThat(commentDao.update(commentDbDto)).isEqualTo(1);
        CommentPostUserDbDto commentDbDto1 = commentDao.findById(commentDbDto.getId());
        assertFalse(
                (commentDbDto.getId() == commentDbDto1.getId()) &&
                        (commentDbDto.getPostId() == commentDbDto1.getPostId()) &&
                        (commentDbDto.getUserId() == commentDbDto1.getUserId()) &&
                        (commentDbDto.getText().equals(commentDbDto1.getText()))
        );

    }

    @Test
    @Transactional
    void deleteSuccess() {
        assertThat(commentDao.deleteById(1)).isEqualTo(1);
        assertThatThrownBy(() -> commentDao.findById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }


    @Test
    @Transactional
    void deleteAllTest() {
        int commentSize = commentDao.findAll(1).size();
        assertThat(commentDao.deleteAll(1)).isEqualTo(commentSize);
    }

}
