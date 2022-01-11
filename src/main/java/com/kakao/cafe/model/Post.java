package com.kakao.cafe.model;

import com.kakao.cafe.dto.PostDto;
import java.time.LocalDateTime;

public class Post {

    private static Long cnt = 1L;

    private final Long id;
    private final User writer;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public Post(Long id, User writer, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public static Post from(PostDto postDto, User writer) {
        return new Post(cnt++, writer, postDto.getTitle(), postDto.getContent(), LocalDateTime.now());
    }
}
