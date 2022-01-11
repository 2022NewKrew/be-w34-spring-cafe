package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class VolatilityArticleStorage implements ArticleDao {
    List<Article> articles = new LinkedList<>();

    @Override
    public List<Article> getPartOfArticles(int startIndex, int finishIndex) {
        if (articles.size() < finishIndex) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    @Override
    public void add(Article article) {
        articles.add(0, article);
    }

    @Override
    public Article findArticleById(int id) {
        return articles
                .stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시글이 없습니다."));
    }

    @Override
    public int getSize() {
        return articles.size();
    }
}
