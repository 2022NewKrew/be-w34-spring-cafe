package com.kakao.cafe.domain;

import com.kakao.cafe.domain.user.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class UserInfoTest {

    @Test
    void hasEqualName() {
        //Given
        UserInfo userInfo = UserInfo.builder()
                .password("123")
                .email("justin@kakao.com")
                .name("justin")
                .userId("justin1")
                .build();
        Assert.isTrue(userInfo.hasEqualId("justin1"), "Error: is Equal Name");
        Assert.isTrue(!userInfo.hasEqualId("jutsin1"), "Error: is Not Equal Name");
    }

}