package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.Users;
import com.kakao.cafe.model.PostModel;

public class Post {

    private Title title;
    private Writer writer;
    private Contents contents;

    public Post(PostModel postModel, Users users){
        this.title = new Title(postModel.getTitle());
        this.writer = users.writer(postModel.getWriter());
        this.contents = new Contents(postModel.getContents());
    }

    public String getTitle() {
        return title.getTitle();
    }

    public User getWriter() {
        return writer.getUser();
    }

    public String getContents() {
        return contents.getContents();
    }
}
