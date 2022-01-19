package com.kakao.cafe.Dto.Post;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
public class PostCreateRequestDto {
    private final Long id;
    private final String title;
    private final String content;
    private static final AtomicLong sequenceId = new AtomicLong();

    public PostCreateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
        this.id = sequenceId.incrementAndGet();
    }
}
