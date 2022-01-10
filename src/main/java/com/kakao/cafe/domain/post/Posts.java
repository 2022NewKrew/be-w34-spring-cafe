package com.kakao.cafe.domain.post;

import java.util.ArrayList;
import java.util.List;

public class Posts {

    private final List<Post> posts;

    public Posts(){
        posts = new ArrayList<>();
    }

    public void add(Post post){
        posts.add(post);
    }

    public Post get(int idx){
        idx-=1;
        if(posts.size() <= idx || idx < 0)
            throw new IllegalArgumentException("존재하지 않는 번호입니다!");
        return posts.get(idx);
    }

    public List<Post> getPosts() {
        return posts;
    }
}
