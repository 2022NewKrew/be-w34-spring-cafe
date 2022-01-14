package com.kakao.cafe.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        assertThat(userRepository.SignUp("alpha","b","c","d@e")).isTrue();
    }

    @Test
    void signUp() {
        assertThat(userRepository.SignUp("alpha","b","c","d@e")).isFalse();
        assertThat(userRepository.SignUp("beta","b","c","d@e")).isTrue();
    }

    @Test
    void getUser() {
        User a = userRepository.getUser("alpha");
        assertThat(a.getUserId()).isEqualTo("alpha");
        assertThat(a.getName()).isEqualTo("c");
        assertThat(a.getEmail()).isEqualTo("d@e");
    }

    @Test
    void getUserLst() {
        List<User> a = userRepository.getUserLst();
        assertThat(a.size()).isEqualTo(1);

        assertThat(userRepository.SignUp("beta","b","c","d@e")).isTrue();
        assertThat(a.size()).isEqualTo(2);

        assertThat(a.get(0).getUserId()).isEqualTo("alpha");
        assertThat(a.get(1).getUserId()).isEqualTo("beta");
    }
}