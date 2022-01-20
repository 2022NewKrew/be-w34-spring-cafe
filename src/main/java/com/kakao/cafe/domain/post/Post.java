package com.kakao.cafe.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private Boolean isRemoved;

    @Builder
    public Post(String writer, String title, String body) {
        this.writer = writer;
        this.title = title;
        this.body = body;
    }
}
