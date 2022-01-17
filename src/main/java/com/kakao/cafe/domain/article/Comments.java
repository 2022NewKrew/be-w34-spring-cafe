package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class Comments {

    private final List<Comment> comments;

    public void add(Comment comment) {
        comments.add(comment);
    }

    public void delete(Comment comment) {
        comments.remove(comment);
    }

    public int getSize() {
        return comments.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments1 = (Comments) o;
        return comments1.getComments().containsAll(comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comments);
    }
}
