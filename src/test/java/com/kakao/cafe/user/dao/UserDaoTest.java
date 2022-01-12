package com.kakao.cafe.user.dao;

import com.kakao.cafe.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    private final UserDao dao = new UserDao();
    private final User user1 = new User("charlie123",
            "test1234",
            "Chanmin Kim",
            "charlie.p1@kakaocorp.com");
    private final User user2 = new User("sample123",
            "test1234",
            "샘플",
            "sample.test@kakaocorp.com");

    @Test
    @DisplayName("주어진 User 객체를 저장할 수 있다.")
    void save() {
        assertDoesNotThrow(() -> dao.save(user1));
    }

    @Test
    @DisplayName("id로 저장된 User 객체를 가져올 수 있다.")
    void fetch() {
        long id = dao.save(user1);
        User fetch = dao.fetch(id);
        assertNotNull(fetch);
    }

    @Test
    @DisplayName("저장된 모든 User 객체를 가져올 수 있다.")
    void fetchAll() {
        dao.save(user1);
        dao.save(user2);
        List<User> users = dao.fetchAll();
        assertEquals(2, users.size());
    }

    @Test
    @DisplayName("사용자 아이디로 User 객체를 가져올 수 있다.")
    void fetchByUserId() {
        dao.save(user1);
        User fetch = dao.fetchByUserId(user1.getUserId());
        assertNotNull(fetch);
    }
}