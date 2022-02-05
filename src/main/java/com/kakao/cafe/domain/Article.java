package com.kakao.cafe.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Article {
    private final Id articleId;
    private final Name writerName;
    private final WriteTime writeTime;
    private final Title title;
    private final Contents contents;
    private final Comments comments;

    public Article(Id articleId, Name writerName, WriteTime writeTime, Title title, Contents contents) {
        this(articleId, writerName, writeTime, title, contents, new Comments());
    }

    public Article(Id articleId, Title title, Contents contents) {
        this(articleId, new Name("default"), new WriteTime(), title, contents, new Comments());
    }

    public Article(Title title, Contents contents) {
        this(new Id(), new Name("default"), new WriteTime(), title, contents, new Comments());
    }
}
