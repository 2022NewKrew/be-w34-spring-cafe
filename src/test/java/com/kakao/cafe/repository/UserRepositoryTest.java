package com.kakao.cafe.repository;

import com.kakao.cafe.entity.User;
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
                .id("id")
                .password("password")
                .name("name")
                .email("email@example.com")
                .build();
        subject.create(user);
    }

    @Test
    void create() {
        User user = new User.Builder()
                .id("id2")
                .password("password")
                .name("name")
                .email("email@example.com")
                .build();

        User result = subject.create(user);

        assertEquals(user, result);
    }

    @Test
    void create_duplicate() {
        User user = new User.Builder()
                .id("id")
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
        User user = subject.get("id");

        assertNotNull(user);
        assertEquals("id", user.getId());
    }

    @Test
    void get_nonExisting() {
        User user = subject.get("id1");

        assertNull(user);
    }

    @Test
    void login() {
        User user = subject.login("id", "password");

        assertNotNull(user);
        assertEquals("id", user.getId());
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
