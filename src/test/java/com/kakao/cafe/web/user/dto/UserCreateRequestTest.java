package com.kakao.cafe.web.user.dto;

import com.kakao.cafe.domain.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserCreateRequestTest {

    @DisplayName("toEntity 테스트")
    @Test
    public void testUserCreateRequestDto() {
        //given
        final UserId userId = new UserId("clo.d");
        final Password password = new Password("1q2w3e4r!Q");
        final Name name = new Name("김동운");
        final Email email = new Email("clo.d@kakaocorp.com");
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