package com.kakao.cafe.web.dto;

import java.util.Date;

public class Post {
    private String user;
    private String title;
    private String content;
    private Integer views;
    private Integer id;
    private Date date;

    public Post(String user, String title, String content, Integer views, Integer id) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.views = views;
        this.id = id;
    }
}
