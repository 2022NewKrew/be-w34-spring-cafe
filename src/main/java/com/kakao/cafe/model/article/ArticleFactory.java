package com.kakao.cafe.model.article;

import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;

public class ArticleFactory {

    public static Article getArticle(ArticleCreateDto articleCreateDto) {
        return new Article(
                0,
                new Title(articleCreateDto.getTitle()),
                new UserId(articleCreateDto.getUserId()),
                new Contents(articleCreateDto.getContents()));
    }

    public static Article getArticle(String title, String userId, String contents) {
        return new Article(
                0,
                new Title(title),
                new UserId(userId),
                new Contents(contents));
    }

    public static Article getArticle(int id, ArticleUpdateDto articleUpdateDto) {
        return new Article(
                id,
                new Title(articleUpdateDto.getTitle()),
                new UserId(articleUpdateDto.getUserId()),
                new Contents(articleUpdateDto.getContents()));
    }
}
