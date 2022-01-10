package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;

public class Writer {
    private User user;
    public Writer(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
