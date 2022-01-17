package com.kakao.cafe.app.data;

import com.kakao.cafe.domain.entity.SignUp;
import com.kakao.cafe.domain.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Sql(scripts = {"classpath:db/sql/schema.sql"})
class JdbcUserRepositoryTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private JdbcUserRepository subject;

    @BeforeEach
    void setUp() {
        subject = new JdbcUserRepository(jdbcTemplate);
        jdbcTemplate.update(
                "INSERT INTO users " +
                        "(user_id, password, name, email) " +
                        "VALUES ('id', 'password', 'name', '')",
                Collections.emptyMap()
        );
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.update(
                "DELETE FROM users",
                Collections.emptyMap()
        );
    }

    @Test
    void create() {
        SignUp signUp = new SignUp("id1", "password", "name", "");

        User result = subject.create(signUp);

        assertEquals(signUp.createUser(result.getId()), result);
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
    void list() {
        List<User> result = subject.list();

        assertEquals(1, result.size());
    }

    @Test
    void getById() {
        long id = subject.list().get(0).getId();

        Optional<User> user = subject.getById(id);

        assertTrue(user.isPresent());
        assertEquals(id, user.get().getId());
    }

    @Test
    void getById_nonExisting() {
        Optional<User> user = subject.getById(999);

        assertTrue(user.isEmpty());
    }

    @Test
    void getByUserId() {
        Optional<User> user = subject.getByUserId("id");

        assertTrue(user.isPresent());
        assertEquals("id", user.get().getUserId());
    }

    @Test
    void getByUserId_nonExisting() {
        Optional<User> user = subject.getByUserId("id1");

        assertTrue(user.isEmpty());
    }

    @Test
    void login() {
        Optional<User> user = subject.login("id", "password");

        assertTrue(user.isPresent());
        assertEquals("id", user.get().getUserId());
    }

    @Test
    void login_wrongId() {
        Optional<User> user = subject.login("id1", "password");

        assertTrue(user.isEmpty());
    }

    @Test
    void login_wrongPassword() {
        Optional<User> user = subject.login("id", "password1");

        assertTrue(user.isEmpty());
    }
}
