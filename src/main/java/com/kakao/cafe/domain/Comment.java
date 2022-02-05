package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Comment {
    private final Id commentId;
    private final Name name;
    private final WriteTime writeTime;
    private final Contents contents;

    public Comment(Contents contents) {
        commentId = new Id();
        name = new Name("Default");
        this.writeTime = new WriteTime();
        this.contents = contents;
    }
}
