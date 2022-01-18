package com.kakao.cafe.model.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {
    private Long id;
    private Long writerId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String formattedCreatedAt() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

}
