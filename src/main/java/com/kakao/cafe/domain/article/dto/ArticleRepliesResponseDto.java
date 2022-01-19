package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleRepliesResponseDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String createdAt;
    private List<ReplyResponseDto> replies;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

    public ArticleRepliesResponseDto(Article article) {
        this.id = article.getId();
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.replies = article.getReplies().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
        this.createdAt = article.getCreatedAt().format(formatter);
    }
}
