package com.kakao.cafe.domain.repository;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository subject;

    @BeforeEach
    void setUp() {
        subject = new UserRepository();
        SignUp signUp = new SignUp("id", "password", "name", "");
        subject.create(signUp);
    }

    @Test
    void create() {
        SignUp signUp = new SignUp("id1", "password", "name", "");

        User result = subject.create(signUp);

        assertEquals(signUp.createUser(1), result);
    }

    @Test
    void create_uniqueId() {
        SignUp signUp1 = new SignUp("id1", "password", "name", "");
        SignUp signUp2 = new SignUp("id2", "password", "name", "");

        User created1 = subject.create(signUp1);
        User created2 = subject.create(signUp2);

        assertNotNull(created1);
        assertNotNull(created2);
        long id1 = created1.getId();
        long id2 = created2.getId();
        assertNotEquals(id1, id2);
    }

    @Test
    void create_duplicate() {
        SignUp signUp = new SignUp("id", "password", "name", "");

        User result = subject.create(signUp);

        assertNull(result);
    }

    @Test
    void list() {
        List<User> result = subject.list();

        assertEquals(1, result.size());
    }

    @Test
    void getById() {
        User user = subject.getById(1);

        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    void getById_nonExisting() {
        User user = subject.getById(999);

        assertNull(user);
    }

    @Test
    void getByUserId() {
        User user = subject.getByUserId("id");

        assertNotNull(user);
        assertEquals("id", user.getUserId());
    }

    @Test
    void getByUserId_nonExisting() {
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
