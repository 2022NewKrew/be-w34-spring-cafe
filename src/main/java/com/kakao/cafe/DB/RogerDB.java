package com.kakao.cafe.DB;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RogerDB {
    List<User> user = new ArrayList<>();
    List<Post> post = new ArrayList<>();

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
