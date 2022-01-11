package com.kakao.cafe.qna;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 2:01
 */
@Data
@NoArgsConstructor
public class ArticleDto {

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents().replace("\r\n", "<br>");
        this.replyCount = article.getReplyCount();
        this.createdDate = article.getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = article
                .getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private int id;

    private String writer;
    private String title;
    private String contents;
    private Integer replyCount;

    private String createdDate;
    private String modifiedDate;
}
