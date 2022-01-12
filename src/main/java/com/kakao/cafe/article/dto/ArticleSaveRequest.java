package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ArticleSaveRequest {

    public final String writer;
    public final String title;
    public final String contents;

    public Article toArticle(User user) {
        return Article.valueOf(user, title, contents);
    }
}
