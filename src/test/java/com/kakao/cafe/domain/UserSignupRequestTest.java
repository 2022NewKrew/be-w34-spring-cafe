package com.kakao.cafe.domain;

import com.kakao.cafe.request.UserSignupRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserSignupRequestTest {

    private final int INITIAL_ID = 0;
    private final String userId = "userId";
    private final String password = "password";
    private final String name = "name";
    private final String email = "email";

    @Test
    @DisplayName("[성공] UserSignupRequest 객체를 생성한다")
    void UserSignupRequest() {
        new UserSignupRequest(userId, password, name, email);
    }

    @Test
    @DisplayName("[성공] Entitty로 올바르게 변환한다")
    void toEntity() {
        UserSignupRequest userSignupRequest = new UserSignupRequest(userId, password, name, email);
        User user_Answer = new User(INITIAL_ID, userId, password, name, email);

        User user = userSignupRequest.toEntity();

        Assertions.assertEquals(user, user_Answer);
    }
}
