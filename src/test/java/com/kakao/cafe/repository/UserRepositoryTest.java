package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        assertThat(userRepository.insert(user2)).isFalse();

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

        Optional<User> userOptional = userRepository.findById(user.getId());
        assertThat(userOptional.get()).isNotNull();
        User userById = userOptional.get();
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
        Optional<User> userOptional = userRepository.findById("nobody");
        assertThat(userOptional.isEmpty()).isTrue();
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
        boolean res = userRepository.update(userNew);
        assertThat(res).isTrue();
        User userRes = userRepository.findById(user.getId()).get();
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
        boolean res = userRepository.update(user);
        assertThat(res).isFalse();
    }

}
