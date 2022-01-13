package com.kakao.cafe.service.article.mapper;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleDtoMapper {

    public List<ArticleInfo> toArticleInfoList(List<Article> articleList) {
        return articleList.stream()
                .map(article -> toArticleInfo(article))
                .collect(Collectors.toList());
    }

    public ArticleInfo toArticleInfo(Article article) {
        return ArticleInfo.builder()
                .id(article.getId())
                .writer(article.getWriter())
                .title(article.getTitle())
                .contents(article.getContents()).build();
    }
}
