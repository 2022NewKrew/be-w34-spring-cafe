package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import lombok.Setter;

@Setter
public class ArticleSaveRequest {

    public final String writer;
    public final String title;
    public final String contents;

    public ArticleSaveRequest(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Article toArticle(User user) {
        return Article.valueOf(user, title, contents);
    }
}
