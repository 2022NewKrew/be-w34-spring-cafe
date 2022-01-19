package com.kakao.cafe.article.entity;

import com.kakao.cafe.reply.entity.Reply;
import com.kakao.cafe.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class Article {

    private Long id;
    private User writer;
    private String title;
    private String contents;
    private List<Reply> replies;
    private LocalDateTime createdAt;
}
