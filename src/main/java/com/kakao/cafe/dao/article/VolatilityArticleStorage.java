package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
@Primary
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
        if (findArticleById(article.getId()) == null) {
            articles.add(article);
            return;
        }
        throw new IllegalArgumentException("중복된 아이디를 갖는 게시들이 존재합니다.");
    }

    @Override
    public Article get(int id) {
        Article article = findArticleById(id);
        if (findArticleById(id) == null) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }
        return article;
    }

    @Override
    public int getSize() {
        return articles.size();
    }

    private Article findArticleById(int id) {
        return articles
                .stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
