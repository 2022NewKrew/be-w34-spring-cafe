package domain;

import domain.user.User;
import domain.user.UserList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {
    UserList userList = UserList.getInstance();

    @Test
    void size() {
        add();
        assertEquals(userList.size(),3);
    }

    @Test
    void add() {
        userList.add(new User("cih468", "1234", "cih468@naver.com"));
        userList.add(new User("kaiden", "1111", "kaiden@kakaocorp.com"));
        userList.add(new User("hendo", "2222", "hendo@kakaocorp.com"));
    }

    @Test
    void getUserList() {
    }

    @Test
    void findByUserId() {
        add();
        assertEquals(userList.findByUserId("cih468").getUserId(), "cih468");
        assertEquals(userList.findByUserId("cih469"), null);
    }

    @Test
    void testToString() {
    }
}