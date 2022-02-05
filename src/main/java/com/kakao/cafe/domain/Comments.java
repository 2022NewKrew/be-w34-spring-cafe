package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Comments {
    private List<Comment> comments;

    public Comments() {
        comments = new ArrayList<>();
    }
}
