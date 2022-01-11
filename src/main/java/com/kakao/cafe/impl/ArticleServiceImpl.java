package com.kakao.cafe.impl;

import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.vo.Article;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    private final List<Article> articleList = new ArrayList<>();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    @Override
    public void insertArticle(Article article) {
        String dateTime = LocalDateTime.now().format(dateTimeFormatter);
        article.setTime(dateTime);

        articleList.add(article);
    }

    @Override
    public List<Article> getArticleList() {
        return articleList;
    }

    @Override
    public Article getArticleByArticleId(String articleId) {
        int articleIndex = Integer.parseInt(articleId) - 1;
        return articleList.get(articleIndex);
    }
}
