package com.kakao.cafe.repository;

import com.kakao.cafe.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserH2RepositoryTest {
    private static User user;
    @Autowired
    private UserRepository userH2Repository;

    @BeforeEach
    void setUp() {
        userH2Repository.deleteById("test");
        user = new User();
        user.setUserId("test");
        user.setPassword("123");
        user.setName("김근욱");
        user.setEmail("test@test.com");
    }

    @Test
    @DisplayName("회원가입 테스트")
    void signUp() {
        userH2Repository.save(user);
        User findUser = userH2Repository.findById("test");
        assertEquals("김근욱", findUser.getName());
    }

    @Test
    @DisplayName("user 전체 목록 조회 테스트")
    void findAll() {
        int beforeSize = userH2Repository.getAllUser().size();
        userH2Repository.save(user);
        int afterSize = userH2Repository.getAllUser().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    @DisplayName("존재하지 않는 id 조회 테스트")
    void existNotUserFind() {
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            userH2Repository.findById("test");
        });
        assertEquals("존재하지 않는 Id 입니다", exception.getMessage());
    }

    @AfterEach
    void init() {
        userH2Repository.deleteById("test");
    }
}