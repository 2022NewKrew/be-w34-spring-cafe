package com.kakao.cafe.domain.post;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime regDateTime;

    public Post() {
    }

    @Builder
    public Post(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        regDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regDateTime=" + regDateTime +
                '}';
    }
}
