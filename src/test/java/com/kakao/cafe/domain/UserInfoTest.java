package com.kakao.cafe.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {

    @Test
    void hasEqualName() {
        UserInfo userInfo = new UserInfo("123","justin","justin@kakaocorp.com");
        Assert.isTrue(userInfo.hasEqualName("justin"),"Error: is Equal Name");
        Assert.isTrue(!userInfo.hasEqualName("jutsin"),"Error: is Not Equal Name");
    }

}