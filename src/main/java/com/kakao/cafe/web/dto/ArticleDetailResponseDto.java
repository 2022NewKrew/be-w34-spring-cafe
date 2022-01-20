package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ArticleDetailResponseDto {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Long id;
    private String writer;
    private String title;
    private String contents;
    private String createdAt;

    private ArticleDetailResponseDto(Long id, String writer, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt.format(FORMATTER);
    }

    public static ArticleDetailResponseDto from(Article article) {
        return new ArticleDetailResponseDto(article.getId(), article.getWriter(), article.getTitle(), article.getContents(), article.getCreatedAt());
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
