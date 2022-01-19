package com.kakao.cafe.domain;

import com.kakao.cafe.dto.CommentDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class Comment {
    private long key;
    private User author;
    private Article article;
    private String content;
    private LocalDateTime postTime;
    private boolean deleted;

    public CommentDTO getDTO() {
        return CommentDTO.builder()
                .key(key)
                .articleKey(article.getKey())
                .authorKey(author.getKey())
                .authorName(author.getName())
                .content(content)
                .postTime(postTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
