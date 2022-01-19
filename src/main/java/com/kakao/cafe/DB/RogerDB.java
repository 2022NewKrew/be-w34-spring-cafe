package com.kakao.cafe.DB;

import com.kakao.cafe.domain.Member;
import com.kakao.cafe.domain.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RogerDB {
    List<Member> member = new ArrayList<>();
    List<Post> post = new ArrayList<>();

    public List<Member> getUser() {
        return member;
    }

    public void setUser(List<Member> member) {
        this.member = member;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
}
