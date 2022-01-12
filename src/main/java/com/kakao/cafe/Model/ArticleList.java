package com.kakao.cafe.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleList {
    private List<ArticleDTO> articleList;
    private Integer totalCount;

    public ArticleList() {
        this.articleList = new ArrayList<>();
        this.totalCount = 0;
    }

    public void add(ArticleDTO articleDTO){
        articleDTO.setArticleIdx(++this.totalCount);
        this.articleList.add(articleDTO);
    }
}
