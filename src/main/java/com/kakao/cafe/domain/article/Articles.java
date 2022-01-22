package com.kakao.cafe.domain.article;

import lombok.Data;

import java.util.List;

@Data
public class Articles {
    private int totalCount;
    private List<Article> articleList;
    private boolean hasPrev = true;
    private List<Integer> pageList;
    private boolean hasNext = true;
    private int prev;
    private int next;
}
