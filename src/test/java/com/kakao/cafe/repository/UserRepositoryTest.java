package com.kakao.cafe.repository;

import com.kakao.cafe.domain.entity.User;
import com.kakao.cafe.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository subject;

    @BeforeEach
    void setUp() {
        subject = new UserRepository();
        User user = new User.Builder()
                .userId("id")
                .password("password")
                .name("name")
                .email("email@example.com")
                .build();
        subject.create(user);
    }

    @Test
    void create() {
        User user = new User.Builder()
                .userId("id2")
                .password("password")
                .name("name")
                .email("email@example.com")
                .build();

        User result = subject.create(user);

        assertEquals(user, result);
    }

    @Test
    void create_uniqueId() {
        User user1 = new User.Builder()
                .userId("id1")
                .password("password")
                .name("name")
                .email("email@example.com")
                .build();
        User user2 = new User.Builder()
                .userId("id2")
                .password("password")
                .name("name")
                .email("email@example.com")
                .build();

        User created1 = subject.create(user1);
        User created2 = subject.create(user2);

        assertNotNull(created1);
        assertNotNull(created2);
        long id1 = created1.getId();
        long id2 = created2.getId();
        assertNotEquals(id1, id2);
    }

    @Test
    void create_duplicate() {
        User user = new User.Builder()
                .userId("id")
                .password("password1")
                .name("name1")
                .email("email1@example.com")
                .build();

        User result = subject.create(user);

        assertNull(result);
    }

    @Test
    void list() {
        List<User> result = subject.list();

        assertEquals(1, result.size());
    }

    @Test
    void get() {
        User user = subject.getByUserId("id");

        assertNotNull(user);
        assertEquals("id", user.getUserId());
    }

    @Test
    void get_nonExisting() {
        User user = subject.getByUserId("id1");

        assertNull(user);
    }

    @Test
    void login() {
        User user = subject.login("id", "password");

        assertNotNull(user);
        assertEquals("id", user.getUserId());
    }

    @Test
    void login_wrongId() {
        User user = subject.login("id1", "password");

        assertNull(user);
    }

    @Test
    void login_wrongPassword() {
        User user = subject.login("id", "password1");

        assertNull(user);
    }
}
