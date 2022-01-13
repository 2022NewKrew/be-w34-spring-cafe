package com.kakao.cafe.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {

    @Test
    void initByBuilderTest() {
        new User.Builder()
                .email("email@kakao")
                .id("2")
                .password("2")
                .name("2").build();
    }


    @Test
    void canModifyTest() {
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        assertThat(user.canModify("yunyul", "1q2w3e4r")).isTrue();
    }

    @Test
    void userEqualsTest() {
        User user1 = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        User user2 = new User.Builder()
                .email("email@naver")
                .id("yunyul")
                .password("4r3e2w1q")
                .name("윤렬2").build();
        assertThat(user1.equals(user2)).isTrue();
    }

}
