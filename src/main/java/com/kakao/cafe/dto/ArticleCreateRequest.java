package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateRequest {

    private String writer;
    private String title;
    private String contents;
    private int userPk;

    public static Article getArticleFromNoDbIndex(ArticleCreateRequest articleCreateRequest) {
        return ModelMapperUtils.getModelMapper()
                .map(articleCreateRequest, Article.class);
    }

}
