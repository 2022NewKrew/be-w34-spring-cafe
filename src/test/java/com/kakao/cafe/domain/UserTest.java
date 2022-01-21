package com.kakao.cafe.domain;

import com.kakao.cafe.controller.dto.UserJoinDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void chcekPassword() {
        //given
        UserJoinDto dto = new UserJoinDto();
        dto.setPassword("1234");
        dto.setEmail("kakao@com");
        dto.setUserId("idid");
        dto.setName("lsh");


        //when
        User user = User.from(dto);
        boolean b = user.chcekPassword("1234");

        //then
        assertThat(b).isTrue();
    }

    @Test
    void updateEmailAndName() {
        // given
        UserJoinDto dto = new UserJoinDto();
        dto.setPassword("1234");
        dto.setEmail("kakao@com");
        dto.setUserId("idid");
        dto.setName("lsh");

        String updateName = "newName";
        String updateEmail = "newEmail";

        // when
        User user = User.from(dto);
        user.updateEmailAndName(updateEmail, updateName);

        // then
        assertThat(user.getEmail()).isEqualTo(updateEmail);
        assertThat(user.getName()).isEqualTo(updateName);
    }
}
