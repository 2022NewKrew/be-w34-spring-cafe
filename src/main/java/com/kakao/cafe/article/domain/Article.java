package com.kakao.cafe.article.domain;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Article {
    private Long id;
    private String title;
    private Long userId;
    private LocalDateTime date;
    private Integer hits;
    private String contents;
}
