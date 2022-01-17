package com.kakao.cafe.domain;

import com.kakao.cafe.domain.user.UserInfo;
import com.kakao.cafe.web.dto.user.UserCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class UserInfoTest {

    @Test
    void hasEqualName() {
        UserInfo userInfo = UserInfo.builder((new UserCreateRequestDto("justin1", "123", "justin", "justin@kakaocorp.com"))).build();
        Assert.isTrue(userInfo.hasEqualId("justin1"), "Error: is Equal Name");
        Assert.isTrue(!userInfo.hasEqualId("jutsin1"), "Error: is Not Equal Name");
    }

}