package com.kakao.cafe.domain;

import com.kakao.cafe.controller.dto.QuestionDto;
import com.kakao.cafe.util.PostIdGenerator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Article {
    private Long postId;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long numOfComment;

    private Article() {}

    public static Article from(QuestionDto dto) {
        Article article = new Article();

        article.setPostId(PostIdGenerator.nextId());
        article.setContent(dto.getContent());
        article.setTitle(dto.getTitle());
        article.setWriter(dto.getWriter());
        article.setCreatedAt(LocalDateTime.now());
        article.setNumOfComment(0L);

        return article;
    }

    private void setPostId(Long postId) {
        this.postId = postId;
    }
}
