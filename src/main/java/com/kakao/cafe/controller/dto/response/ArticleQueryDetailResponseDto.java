package com.kakao.cafe.controller.dto.response;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleQueryDetailResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String writerId;
    private List<ReplyQueryResponseDto> replyQueryResponseDtos;

    public ArticleQueryDetailResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.writerId = article.getWriter().getUserId();
        if(article.getReplys()!=null) {
            this.replyQueryResponseDtos = article.getReplys().stream()
                    .map(ReplyQueryResponseDto::new)
                    .collect(Collectors.toUnmodifiableList());
        }
    }
}
