package com.kakao.cafe.domain;

import com.kakao.cafe.domain.user.UserInfo;
import com.kakao.cafe.domain.user.UserList;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class UserListTest {
    UserListTest() {
        UserList userList = UserList.getInstance();
        userList.addUser(new UserInfo("justin", "123", "justin", "justin@kakao.com"));
        userList.addUser(new UserInfo("justi", "123", "justin", "justi@kakao.com"));
        userList.addUser(new UserInfo("just", "123", "justin", "just@kakao.com"));
    }

    @Test
    void addUser() {
        Assert.isTrue(UserList.getInstance().getUserList().get(0).hasEqualId("justin"), "list.html not added correctly!");
        Assert.isTrue(UserList.getInstance().getUserList().get(1).hasEqualId("justi"), "list.html not added correctly!");
        Assert.isTrue(UserList.getInstance().getUserList().get(2).hasEqualId("just"), "list.html not added correctly!");
    }

    @Test
    void getSize() {
        Assert.isTrue(UserList.getInstance().getSize() == 3, "sizeError!");
    }

    @Test
    void findByName() {
        Assert.isTrue(UserList.getInstance().findById("justi") != null, "find by Name Error");
        Assert.isTrue(UserList.getInstance().findById("justi").hasEqualId("justi"), "find by Name Error");
    }
}