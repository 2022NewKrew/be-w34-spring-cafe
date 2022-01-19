package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Article {

    private final Long id;
    private final String writer;
    private final String title;
    private final String contents;
    private boolean deleted = false;

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, title, contents, deleted);
    }

    public void shiftIsDeleted() {
        this.deleted = !this.deleted;
    }
}
