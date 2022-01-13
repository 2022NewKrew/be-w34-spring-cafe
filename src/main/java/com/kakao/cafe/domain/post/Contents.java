package com.kakao.cafe.domain.post;

public class Contents {

    private final String contents;

    public Contents(String contents) {
        if (contents == null) contents = "";
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
