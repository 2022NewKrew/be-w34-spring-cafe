package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.model.PostModel;
import org.junit.jupiter.api.Test;

public class PostTest {
    @Test
    void makePostByModel(){
        Users users = new Users(new User.Builder()
                .id("writer1")
                .password("1q2w3e4r")
                .name("name1")
                .email("writer@kakao.com")
                .build());
        PostModel postModel = new PostModel("writer1", "hello", "world");
        new Post(postModel);
    }
}
