package com.kakao.cafe.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Reply {
    private Long id;
    private Long authorId;
    private String author;
    private Long articleId;
    private String content;
    private Date createdAt;
}
