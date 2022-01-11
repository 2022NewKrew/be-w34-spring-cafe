package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemoryUserRepositoryTest {

    MemoryUserRepository memoryUserRepository;

    @BeforeEach
    void setUp() {
        memoryUserRepository = new MemoryUserRepository();
    }

    @Test
    @DisplayName("아이디로 회원 조회")
    void testOfFindUserByUserId() {
        User user = User.builder()
                .userId("leaf")
                .build();
        memoryUserRepository.save(user);

        User foundMember = memoryUserRepository.findByUserId("leaf").get();
        assertThat(foundMember).isEqualTo(user);
    }

    @Test
    @DisplayName("회원 전체 조회")
    void testOfFindUsers() {
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        User user3 = User.builder().build();
        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);
        memoryUserRepository.save(user3);

        List<User> users = memoryUserRepository.findAll();
        assertThat(users.size()).isEqualTo(3);
        assertThat(users).contains(user1, user2, user3);
    }

}