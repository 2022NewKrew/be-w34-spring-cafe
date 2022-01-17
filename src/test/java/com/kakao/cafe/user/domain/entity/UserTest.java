package com.kakao.cafe.user.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;

class UserTest {
    @ParameterizedTest
    @DisplayName("잘못된 파라미터가 주어질때 User 생성 실패")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getWrongConstructParameters")
    void failedCreateWhenWrongParameters(String userId, String password, String name, String email){
        assertThatThrownBy(() -> {
            UserInfo userInfo = new UserInfo(name, email);
            userInfo.validate();
            new User(userId, password, userInfo).validate();
        }).isInstanceOfAny(IllegalArgumentException.class, NullPointerException.class);
    }

    @ParameterizedTest
    @DisplayName("사용자 정보 변경 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUsers")
    void successUpdateUserInfo(User user){
        //given
        UserInfo userInfo = new UserInfo(user.getUserInfo().getName().concat("2"), "e".concat(user.getUserInfo().getEmail()));

        //when
        user.updateInfo(userInfo);

        //then
        assertThat(user.getUserInfo()).isEqualTo(userInfo);
    }
}