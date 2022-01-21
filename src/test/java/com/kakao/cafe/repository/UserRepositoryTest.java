package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Transactional
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void insertDuplicateFailTest() {
        User user = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        userRepository.insert(user);
        User user2 = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();

        assertThatThrownBy(() -> userRepository.insert(user2))
                .isInstanceOf(DuplicateKeyException.class);

    }

    @Test
    void insertAndFindByIdTest() {
        User user = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        userRepository.insert(user);

        User userById = userRepository.findById(user.getId());
        assertThat(userById.getId()).isEqualTo(user.getId());

    }

    @Test
    void findNullTest() {
        User user = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        userRepository.insert(user);
        assertThatThrownBy(() -> userRepository.findById("nobody"))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    @Test
    void findAllTest() {
        User user = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        userRepository.insert(user);
        Users users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void findAllEmptyTest() {
        Users users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    void updateTest() {
        User user = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        User userNew = new User.Builder()
                .name("윤렬new")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        userRepository.insert(user);
        int res = userRepository.update(userNew);
        assertThat(res).isEqualTo(1);
        User userRes = userRepository.findById(user.getId());
        assertThat(userRes.getId()).isEqualTo(userNew.getId());

    }

    @Test
    void updateFailTest() {
        User user = new User.Builder()
                .name("윤렬")
                .id("yunyul")
                .password("asdf")
                .email("yunyul3@gmail.com")
                .build();
        int res = userRepository.update(user);
        assertThat(res).isEqualTo(0);
    }

}
