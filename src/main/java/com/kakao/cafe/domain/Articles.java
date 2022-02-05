package com.kakao.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Articles {
    private List<Article> articles;
}
