package com.kakao.cafe.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UsersTest {

    @Test
    void isUserIdTest(){
        Users users = new Users();
        User user1 = new User("email1@kakao.com", "id1", "testman", "testtest");
        User user2 = new User("email1@kakao.com", "id2", "testman", "testtest");
        users.add(user1);
        users.add(user2);

        User target = users.getByUserId("id1");
        assertThat(target.getId()).isEqualTo("id1");
    }

    @Test
    void userChangeDoneTest() {
        User user1 = new User("email1@kakao.com", "id1", "testman", "testtest1");
        User user2 = new User("email1@kakao.com", "id2", "testman", "testtest2");
        Users users = new Users(user1, user2);
        User newUser1 = new User("email1@naver.com", "id1", "manman", "manman1");
        assertThat(users.update("id1", "testtest1", newUser1)).isTrue();
        User target = users.getByUserId("id1");
        assertThat(target.getName()).isEqualTo("manman");
    }

}
