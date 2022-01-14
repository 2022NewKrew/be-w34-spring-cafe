package com.kakao.cafe.domain;

import com.kakao.cafe.domain.user.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class UserInfoTest {

    @Test
    void hasEqualName() {
        UserInfo userInfo = new UserInfo("justin1", "123", "justin", "justin@kakaocorp.com");
        Assert.isTrue(userInfo.hasEqualId("justin1"), "Error: is Equal Name");
        Assert.isTrue(!userInfo.hasEqualId("jutsin1"), "Error: is Not Equal Name");
    }

}