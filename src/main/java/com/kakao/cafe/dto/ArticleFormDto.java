package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class ArticleFormDto {
    private String writer;
    private String title;
    private String contents;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ArticleFormDto articleFromDto = (ArticleFormDto) object;
        return Objects.equals(writer, articleFromDto.writer) &&
                Objects.equals(title, articleFromDto.title) &&
                Objects.equals(contents, articleFromDto.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(writer, title, contents);
    }
}
