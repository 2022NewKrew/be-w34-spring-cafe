package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CreateUserDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void createUserTest() {
        //given
        String userId = "jake";
        String password = "0208";
        String name = "jake";
        String email = "jake@kakao.com";

        //when
        CreateUserDto createUserDto = new CreateUserDto(userId, password, name, email);
        User user = new User(createUserDto);

        //then
        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo("jake");
    }


}
