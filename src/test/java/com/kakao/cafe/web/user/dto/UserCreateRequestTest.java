package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserCreateRequestTest {

    @DisplayName("toEntity 테스트")
    @Test
    public void testUserCreateRequestDto() {
        //given
        final String userId = "clo.d";
        final String password = "1234";
        final String name = "dongwoon";
        final String email = "clo.d@kakaocorp.com";
        final UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);

        //when
        final User user = dto.toEntity();

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getPassword()).isEqualTo(password);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
    }
}