package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user = new User("cih468", "1234", "cih468@naver.com");

    @Test
    void testToString() {
        assertEquals(user.toString(), "User{userId='cih468', password='1234', email='cih468@naver.com', registerDate='2022-01-11'}");
    }

    @Test
    void getUserId() {
        assertEquals(user.getUserId(), "cih468");
    }
}