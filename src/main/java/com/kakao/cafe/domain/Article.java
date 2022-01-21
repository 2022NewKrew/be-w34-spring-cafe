package com.kakao.cafe.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
public class Article {

    private Long id;
    private User writer;
    private List<Reply> replys;
    private String title;
    private String contents;

    public void setId(long id) {
        this.id = id;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public void update(String updateTitle, String updateContents) {
        title = updateTitle;
        contents = updateContents;
    }

    public void addReply(Reply reply) {
        if (replys == null) {
            replys = new ArrayList<>();
        }
        replys.add(reply);
    }
}
