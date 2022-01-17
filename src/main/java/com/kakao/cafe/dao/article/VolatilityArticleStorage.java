package com.kakao.cafe.dao.article;

import com.kakao.cafe.model.article.Article;
import com.kakao.cafe.model.article.Contents;
import com.kakao.cafe.model.article.Title;
import com.kakao.cafe.model.article.Writer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

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
    public void addArticle(Title title, Writer writer, Contents contents) {
        articles.add(FRONT_OF_LIST, new Article(nextArticleId.getAndIncrement(), title, writer, contents));
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
}
