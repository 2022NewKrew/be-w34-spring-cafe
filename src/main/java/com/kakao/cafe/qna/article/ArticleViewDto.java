package com.kakao.cafe.qna.article;

import com.kakao.cafe.qna.comment.CommentDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melodist
 * Date: 2022-01-11 011
 * Time: 오후 2:01
 */
@Data
@NoArgsConstructor
public class ArticleViewDto {

    public ArticleViewDto(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents().replace("\r\n", "<br>");
        this.createdDate = article.getCreatedDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = article
                .getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.comments = article.getComments()
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
        this.commentsCount = article.getCommentsCount();
    }

    private int id;

    private String writer;
    private String title;
    private String contents;

    private String createdDate;
    private String modifiedDate;

    private List<CommentDto> comments;
    private Integer commentsCount;
}
