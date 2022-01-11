package com.kakao.cafe.DTO;

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
        articleDTO.setPostIdx(++this.totalCount);
        this.articleList.add(articleDTO);
    }

    @Override
    public String toString() {
        return "PostList{" +
                "postList=" + articleList +
                ", totalCount=" + totalCount +
                '}';
    }
}
