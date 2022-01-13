package com.kakao.cafe.user.repository;

import com.kakao.cafe.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserDBRepositoryImpl userRepository;


    @Test
    @DisplayName("유저 저장과 조회 확인")
    void testPersistAndFind() {
        // given
        UserCreateRequestDTO user1 = new UserCreateRequestDTO("myId1", "email1@gmail.com", "춘식이", "12345", LocalDateTime.now());
        UserCreateRequestDTO user2 = new UserCreateRequestDTO("myId2", "email2@gmail.com", "라이언", "abcd", LocalDateTime.now());
        UserCreateRequestDTO user3 = new UserCreateRequestDTO("myId3", "email3@gmail.com", "무지", "1q2w3e4", LocalDateTime.now());

        // when
        Long id1 = userRepository.persist(user1);
        Long id2 = userRepository.persist(user2);
        Long id3 = userRepository.persist(user3);

        // then
        User findUser1 = userRepository.find(id1);
        assertThat(findUser1.getId()).isEqualTo(id1);

        User findUser2 = userRepository.find(id2);
        assertThat(findUser2.getNickName()).isEqualTo("라이언");
    }
}
