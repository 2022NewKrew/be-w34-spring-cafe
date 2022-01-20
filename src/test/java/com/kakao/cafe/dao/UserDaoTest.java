package com.kakao.cafe.dao;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Transactional
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userDao.deleteAll();
    }

    @Test
    void insertDupFailTest() {
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        userDao.insert(user);
        User user2 = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        assertThatThrownBy(() -> userDao.insert(user2)).isInstanceOf(DuplicateKeyException.class);
    }


    @Test
    void insertAndFindTest() {
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        userDao.insert(user);
        User user2 = userDao.findById(user.getId());
        assertThat(
                (user.getEmail().equals(user2.getEmail())) &&
                        (user.getPassword().equals(user2.getPassword())) &&
                        (user.getName().equals(user2.getName()))
        ).isTrue();

    }

    @Test
    void findNullTest() {
        assertThatThrownBy(() -> userDao.findById("notExist"))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }


    @Test
    void findAllTest() {
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();
        User user2 = new User.Builder()
                .email("email@kakao")
                .id("yunyul2")
                .password("1q2w3e4r")
                .name("윤렬").build();
        userDao.insert(user);
        userDao.insert(user2);
        Users users = userDao.findAll();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    void findAllEmpty() {
        assertThat(userDao.findAll().size()).isEqualTo(0);
    }

    @Test
    void userUpdateTest() {
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();
        User user2 = new User.Builder()
                .email("email@naver")
                .id("yunyul")
                .password("qwert12345")
                .name("윤렬change").build();
        userDao.insert(user);
        userDao.update(user2);
        User newInfo = userDao.findById(user.getId());
        assertThat(newInfo.getEmail().equals(user2.getEmail())).isTrue();

    }

    @Test
    void userUpdateFail() {
        User user2 = new User.Builder()
                .email("email@naver")
                .id("yunyul")
                .password("qwert12345")
                .name("윤렬change").build();
        assertThat(userDao.update(user2)).isEqualTo(0);
    }


}
