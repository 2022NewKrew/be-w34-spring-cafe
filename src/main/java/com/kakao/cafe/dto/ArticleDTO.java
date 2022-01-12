package com.kakao.cafe.dto;

import com.kakao.cafe.persistence.model.Article;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ArticleDTO {

    @Getter
    @AllArgsConstructor
    class Create {

        @NotBlank
        String author;
        @NotBlank
        String title;
        @NotBlank
        String body;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    class Result {

        static final String dateTimePattern = "yyyy-MM-dd HH:mm";

        Long id;
        String authorUserId;
        String authorName;
        String title;
        String body;
        String createdAt;

        public static Result from(Article article) {
            return new Result(article.getId(), article.getAuthorUserId(),
                article.getAuthorName(), article.getTitle(), article.getBody(),
                article.getCreatedAt().format(DateTimeFormatter.ofPattern(dateTimePattern)));
        }
    }
}
