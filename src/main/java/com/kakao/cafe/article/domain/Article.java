package com.kakao.cafe.article.domain;

import com.kakao.cafe.exception.AccessDeniedException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class Article {
    private Long id;
    private Long userFk;
    private String writer;
    private String title;
    private String contents;
    private String writingTime;
    private Long countOfComment;

    public Article(Long userFK, String writer, String title, String contents) {
        this.userFk = userFK;
        this.writer = writer;
        this.contents = contents;
        this.title = title;
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.countOfComment = 0L;
    }

    public Article(Long id, Long userFk, String writer, String title, String contents, String writingTime, Long countOfComment) {
        this.id = id;
        this.userFk = userFk;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writingTime = writingTime;
        this.countOfComment = countOfComment;
    }

    public Article(Long id, Long userFK, String writer, String title, String contents, Long countOfComment) {
        this.id = id;
        this.userFk = userFK;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.writingTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.countOfComment = countOfComment;
    }

    public void setArticleId(Long id) {
        this.id = id;
    }

    public void validateAccessUpdateArticle(Long userFK) {
        if (userFK != this.userFk) {
            throw new AccessDeniedException("해당 게시물을 수정 할 수 있는 권한이 없습니다.");
        }
    }

    public void validateAccessDeleteArticle(Long userFK) {
        if (userFK != this.userFk) {
            throw new AccessDeniedException("해당 게시물을 삭제 할 수 있는 권한이 없습니다.");
        }
    }
}
