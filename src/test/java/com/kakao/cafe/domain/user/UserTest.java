package com.kakao.cafe.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {

    @Test
    void initByBuilderTest(){
        new User.Builder()
                .email("email@kakao")
                .id("2")
                .password("2")
                .name("2").build();
    }

    @Test
    void canModifyTest(){
        User user = new User.Builder()
                .email("email@kakao")
                .id("yunyul")
                .password("1q2w3e4r")
                .name("윤렬").build();

        assertThat(user.canModify("yunyul", "1q2w3e4r")).isEqualTo(true);
    }
}
