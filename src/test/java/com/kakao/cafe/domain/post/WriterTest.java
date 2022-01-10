package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WriterTest {

    @Test
    void notInUsersTest(){
        Users users = new Users(new User.Builder().id("1").email("email1@kakao").password("1").name("1").build(),
                new User.Builder().id("2").email("email2@kakao").password("2").name("2").build());
        assertThatThrownBy(()->{
            Writer writer = users.writer("3");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
