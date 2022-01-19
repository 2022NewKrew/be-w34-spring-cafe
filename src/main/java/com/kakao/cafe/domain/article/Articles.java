package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Articles {
    private int totalCount;
    private List<Article> articleList;
    private boolean hasPrev = true;
    private List<Integer> pageList;
    private boolean hasNext = true;
    private int prev;
    private int next;
}
