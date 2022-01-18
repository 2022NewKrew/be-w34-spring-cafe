package com.kakao.cafe.domain;

import com.kakao.cafe.domain.user.dto.UserJoinForm;
import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void chcekPassword() {
        //given
        UserJoinForm dto = new UserJoinForm();
        dto.setPassword("1234");
        dto.setEmail("kakao@com");
        dto.setUserId("idid");
        dto.setName("lsh");


        //when
        User user = dto.toUser();
        boolean b = user.chcekPassword("1234");

        //then
        assertThat(b).isTrue();
    }

    @Test
    void updateEmailAndName() {
        // given
        UserJoinForm dto = new UserJoinForm();
        dto.setPassword("1234");
        dto.setEmail("kakao@com");
        dto.setUserId("idid");
        dto.setName("lsh");

        String updateName = "newName";
        String updateEmail = "newEmail";

        // when
        User user = dto.toUser();
        user.updateEmailAndName(updateEmail, updateName);

        // then
        assertThat(user.getEmail()).isEqualTo(updateEmail);
        assertThat(user.getName()).isEqualTo(updateName);
    }
}
