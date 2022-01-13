package com.kakao.cafe.controller.articles.mapper;

import com.kakao.cafe.controller.articles.dto.response.ArticleDetailResponse;
import com.kakao.cafe.controller.articles.dto.response.ArticleItemResponse;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleViewMapper {

    public List<ArticleItemResponse> toArticleItemResponseList(List<ArticleInfo> articleInfoList) {
        return articleInfoList.stream()
                .map(articleInfo -> toArticleItemResponse(articleInfo))
                .collect(Collectors.toList());
    }

    public ArticleItemResponse toArticleItemResponse(ArticleInfo articleInfo) {
        return ArticleItemResponse.builder()
                .id(articleInfo.getId())
                .title(articleInfo.getTitle())
                .writer(articleInfo.getWriter())
                .build();
    }

    public ArticleDetailResponse toArticleDetailResponse(ArticleInfo articleInfo) {
        return ArticleDetailResponse.builder()
                .writer(articleInfo.getWriter())
                .title(articleInfo.getTitle())
                .contents(articleInfo.getContents())
                .build();
    }
}
