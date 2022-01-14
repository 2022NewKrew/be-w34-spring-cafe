package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InquireArticleDto {

    private Long articleId;
    private Long writerId;
    private String title;
    private String contents;
    private String writer;
    private String time;
    private List<CommentDto> comments;

    public int getSize() {
        return comments.size();
    }
}
