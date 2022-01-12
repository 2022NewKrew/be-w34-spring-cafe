package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.Article;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@Primary
public class VolatilityArticleStorage implements ArticleDao {
    private static final AtomicInteger nextArticleId = new AtomicInteger(1);
    List<Article> articles = new LinkedList<>();

    @Override
    public List<Article> getArticles(int pageNumber, int ArticlesPerPage) {
        int startIndex = (pageNumber - 1) * ArticlesPerPage;
        int finishIndex = ArticlesPerPage * (pageNumber);
        if (articles.size() < finishIndex) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    @Override
    public void addArticle(String title, String writer, String contents) {
        articles.add(0, new Article(nextArticleId.getAndIncrement(), title, writer, contents));
    }

    @Override
    public Article findArticleById(int id) {
        return articles
                .stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    @Override
    public int getSize() {
        return articles.size();
    }
}
