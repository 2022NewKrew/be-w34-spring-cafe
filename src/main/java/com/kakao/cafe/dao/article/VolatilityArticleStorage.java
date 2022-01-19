package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;

@Repository
public class VolatilityArticleStorage implements ArticleDao {

    public static final int FRONT_OF_LIST = 0;

    private final AtomicInteger nextArticleId = new AtomicInteger(1);
    List<Article> articles = new LinkedList<>();

    @Override
    public List<Article> getArticles(int pageNumber, int articlesPerPage) {
        int startIndex = (pageNumber - 1) * articlesPerPage;
        int finishIndex = articlesPerPage * pageNumber;
        if (articles.size() < finishIndex) {
            return new ArrayList<>(articles.subList(startIndex, articles.size()));
        }
        return new ArrayList<>(articles.subList(startIndex, finishIndex));
    }

    @Override
    public void addArticle(Article article) {
        articles.add(
                FRONT_OF_LIST,
                new Article(
                        nextArticleId.getAndIncrement(),
                        article.getTitle(),
                        article.getWriter(),
                        article.getContents()));
    }

    @Override
    public Optional<Article> findArticleById(int id) {
        return articles
                .stream()
                .filter(article -> article.getId() == id)
                .findFirst();
    }

    @Override
    public int getSize() {
        return articles.size();
    }

    @Override
    public void deleteArticle(int id) {
        Article targetArticle = articles
                .stream()
                .filter(article -> article.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾는 게시물이 없습니다."));
        articles.remove(targetArticle);
    }

    @Override
    public void updateArticle(Article updateArticle) {
        Article targetArticle = articles
                .stream()
                .filter(article -> article.getId() == updateArticle.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게시물이 존재하지 않습니다."));
        targetArticle.setTitle(updateArticle.getTitle());
        targetArticle.setContents(updateArticle.getContents());
    }
}
