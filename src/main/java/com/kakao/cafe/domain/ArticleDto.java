package com.kakao.cafe.domain;

import com.kakao.cafe.util.Pretty;
import org.springframework.lang.NonNull;

import java.time.ZoneId;
import java.util.Locale;

public class ArticleDto {
    private long idx;
    private String author;
    private String title;
    private String body;
    private String[] bodyLines;
    private String createdAtPretty;

    static public ArticleDto from(@NonNull final Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setIdx(article.getIdx());
        articleDto.setAuthor(article.getAuthor());
        articleDto.setTitle(article.getTitle());
        articleDto.setBody(article.getBody());
        articleDto.setBodyLines(
                Pretty.splitByNewLine(article.getBody())
        );
        articleDto.setCreatedAtPretty(
                Pretty.epochSecond(article.getCreatedAt(), Locale.KOREA, ZoneId.of("Asia/Seoul"))
        );
        return articleDto;
    }

    public long getIdx() {
        return idx;
    }

    public void setIdx(long idx) {
        this.idx = idx;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getBodyLines() {
        return bodyLines;
    }

    public void setBodyLines(String[] bodyLines) {
        this.bodyLines = bodyLines;
    }

    public String getCreatedAtPretty() {
        return createdAtPretty;
    }

    public void setCreatedAtPretty(String createAtPretty) {
        this.createdAtPretty = createAtPretty;
    }
}
