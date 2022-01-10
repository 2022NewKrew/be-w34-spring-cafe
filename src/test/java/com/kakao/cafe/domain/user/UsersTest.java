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

}
