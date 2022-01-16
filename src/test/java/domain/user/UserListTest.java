package domain.user;


import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserList;
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
    void findByUserId() {
        add();
        assertEquals(userList.findByUserId("cih468").getUserId(), "cih468");
        assertNull(userList.findByUserId("cih469"));
    }

    @Test
    void testToString() {
    }
}