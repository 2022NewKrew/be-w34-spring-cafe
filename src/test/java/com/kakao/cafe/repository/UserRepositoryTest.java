package com.kakao.cafe.repository;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    void insertAndFindTest() {
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        userRepository.insert(user);
        User user2 = userRepository.findById(user.getId());
        assertThat(
                (user.getEmail().equals(user2.getEmail())) &&
                        (user.getPassword().equals(user2.getPassword())) &&
                        (user.getName().equals(user2.getName()))
        ).isTrue();

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
        userRepository.insert(user);
        userRepository.insert(user2);
        Users users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(2);
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
        userRepository.insert(user);
        userRepository.update(user2);

        User newInfo = userRepository.findById(user.getId());
        assertThat(newInfo.getEmail().equals(user2.getEmail())).isTrue();

    }


}
