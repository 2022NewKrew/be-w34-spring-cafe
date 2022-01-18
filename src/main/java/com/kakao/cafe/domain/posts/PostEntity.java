package com.kakao.cafe.domain.posts;

import com.kakao.cafe.domain.BaseTimeEntity;
import lombok.Getter;

@Getter
public class PostEntity extends BaseTimeEntity {

    private String writer;
    private String title;
    private String content;

    public PostEntity (String title, String content, String writer) {
        super();
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

}
