package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Comment {

    private final Long id;
    private final Long articleId;
    private final String commenter;
    private final String contents;
    private boolean deleted = false;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public void shiftIsDeleted() {
        this.deleted = !this.deleted;
    }
}
