package com.kakao.cafe.domain;

import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        assertThat(userRepository.signUp("alpha","b","c","d@e")).isTrue();
    }

    @Test
    void signUp() {
        assertThat(userRepository.signUp("alpha","b","c","d@e")).isFalse();
        assertThat(userRepository.signUp("beta","b","c","d@e")).isTrue();
    }

    @Test
    void getUser() {
        User setUpUser = userRepository.getUser("alpha");
        assertThat(setUpUser.getUserId()).isEqualTo("alpha");
        assertThat(setUpUser.getName()).isEqualTo("c");
        assertThat(setUpUser.getEmail()).isEqualTo("d@e");
    }

    @Test
    void getUserLst() {
        List<User> userLst = userRepository.getUserLst();
        assertThat(userLst.size()).isEqualTo(1);

        assertThat(userRepository.signUp("beta","b","c","d@e")).isTrue();
        assertThat(userLst.size()).isEqualTo(2);

        assertThat(userLst.get(0).getUserId()).isEqualTo("alpha");
        assertThat(userLst.get(1).getUserId()).isEqualTo("beta");
    }
}