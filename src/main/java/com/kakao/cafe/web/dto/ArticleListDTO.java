package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class ArticleListDTO {
    private final List<ArticleDTO> articleListDTO;

    public ArticleListDTO(List<Article> articleList) {
        articleListDTO = articleList.stream().map(ArticleDTO::new).collect(Collectors.toUnmodifiableList());
    }

    public List<ArticleDTO> getCopiedUserList() {
        return new ArrayList<>(articleListDTO);
    }
}
