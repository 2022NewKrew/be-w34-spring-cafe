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
        User user = new User("leaf", "1234", "김남현", "leaf.hyeon@kakaocorp.com");
        memoryUserRepository.save(user);

        User foundMember = memoryUserRepository.findUserByUserId("leaf").get();
        assertThat(foundMember).isEqualTo(user);
    }

    @Test
    @DisplayName("회원 전체 조회")
    void testOfFindUsers() {
        User user1 = new User("leaf1", "1234", "김남현1", "leaf1.hyeon@kakaocorp.com");
        User user2 = new User("leaf2", "1234", "김남현2", "leaf2.hyeon@kakaocorp.com");
        User user3 = new User("leaf3", "1234", "김남현3", "leaf3.hyeon@kakaocorp.com");
        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);
        memoryUserRepository.save(user3);

        List<User> users = memoryUserRepository.findAll();
        assertThat(users.size()).isEqualTo(3);
        assertThat(users).contains(user1, user2, user3);
    }

}