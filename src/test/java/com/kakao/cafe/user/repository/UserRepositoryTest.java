package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

    UserRepository userRepository = UserRepository.getRepository();

    @BeforeEach
    void init() {
        userRepository.clear();
    }


    @Test
    @DisplayName("유저 저장과 조회 확인")
    void testPersistAndFind() {
        // given
        CreateUserDTO user1 = new CreateUserDTO("email1@gmail.com", "춘식이", "12345", LocalDateTime.now());
        CreateUserDTO user2 = new CreateUserDTO("email2@gmail.com", "라이언", "abcd", LocalDateTime.now());
        CreateUserDTO user3 = new CreateUserDTO("email3@gmail.com", "무지", "1q2w3e4", LocalDateTime.now());

        // when
        userRepository.persist(user1);
        userRepository.persist(user2);
        userRepository.persist(user3);

        // then
        User findUser1 = userRepository.find(1L);
        assertThat(findUser1.getId()).isEqualTo(1L);

        User findUser2 = userRepository.find(1L);
        assertThat(findUser2.getNickName()).isEqualTo("라이언");

        ArrayList<User> findUser = userRepository.findAll();
        assertThat(findUser.size()).isEqualTo(3);
    }
}
