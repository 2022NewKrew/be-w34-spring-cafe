package com.kakao.cafe.domain;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class UserListTest {
    UserListTest() {
        UserList userList = UserList.getInstance();
        userList.addUser(new UserInfo("123", "justin", "justin@kakao.com"));
        userList.addUser(new UserInfo("123", "justi", "justi@kakao.com"));
        userList.addUser(new UserInfo("123", "just", "just@kakao.com"));
    }

    @Test
    void addUser() {
        Assert.isTrue(UserList.getInstance().getUserList().get(0).hasEqualName("justin"),"list not added correctly!");
        Assert.isTrue(UserList.getInstance().getUserList().get(0).hasEqualName("justi"),"list not added correctly!");
        Assert.isTrue(UserList.getInstance().getUserList().get(0).hasEqualName("just"),"list not added correctly!");
    }

    @Test
    void getSize() {
        Assert.isTrue(UserList.getInstance().getSize()==3,"sizeError!");
    }

    @Test
    void findByName() {
        Assert.isTrue(UserList.getInstance().findByName("justi")!=null,"find by Name Error");
        Assert.isTrue(UserList.getInstance().findByName("justi").hasEqualName("justi"),"find by Name Error");
    }
}