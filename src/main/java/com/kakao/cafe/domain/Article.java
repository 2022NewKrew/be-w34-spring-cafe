package com.kakao.cafe.domain;

import com.kakao.cafe.dto.ArticleCreateRequest;
import com.kakao.cafe.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Integer id;
    private String writer;
    private String title;
    private String contents;

    public static Article fromNoDbIndex(ArticleCreateRequest articleCreateRequest) {
        return ModelMapperUtils.getModelMapper()
                .map(articleCreateRequest, Article.class);
    }

    public Integer getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
