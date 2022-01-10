package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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
        Assertions.assertThat(foundMember).isEqualTo(user);
    }

}