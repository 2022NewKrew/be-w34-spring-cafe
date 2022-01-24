package com.kakao.cafe.model.repository;

import com.kakao.cafe.model.domain.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Repository("ArticleRepositoryMemoryImpl")
public class ArticleRepositoryMemoryImpl implements ArticleRepository {
    static final Map<Long, Article> STORED_ARTICLES = new LinkedHashMap<>();
    private static long maxArticleId = 1L;

    @Override
    public boolean saveArticle(Article article) {
        article.setArticleId(maxArticleId++);
        article.setCreatedDate(LocalDateTime.now());
        article.setFormattedCreatedDate(article.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        STORED_ARTICLES.put(article.getArticleId(), article);
        return true;
    }

    @Override
    public List<Article> findAllArticle() {
        return new ArrayList<>(STORED_ARTICLES.values());
    }

    @Override
    public Optional<Article> findArticleByArticleId(long articleId) {
        return Optional.ofNullable(STORED_ARTICLES.get(articleId));
    }

    @Override
    public List<Article> findArticlesByWriterId(String writerId) {
        return STORED_ARTICLES.values().stream()
                .filter(article -> article.getWriterId().equals(writerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findArticlesByStartAndWantedCountPerPage(long start, long countPerPage) {
        long total = STORED_ARTICLES.size();

        List<Article> sortedList = STORED_ARTICLES.values().stream()
                .sorted((a1, a2) -> Long.compare(a2.getArticleId(), a1.getArticleId()))
                .collect(Collectors.toList());

        if (total < countPerPage) {
            return sortedList;
        }

        long end = Math.min(start + countPerPage - 1, total);
        return sortedList.subList((int) start, (int) end);
    }

    @Override
    public long countAllAvailableArticles() {
        return STORED_ARTICLES.values().size();
    }

    @Override
    public boolean modifyArticle(Article article) {
        long articleId = article.getArticleId();

        if (!STORED_ARTICLES.containsKey(articleId)) {
            return false;
        }

        Article originalArticle = STORED_ARTICLES.get(articleId);

        STORED_ARTICLES.put(articleId, Article.builder()
                .articleId(articleId)
                .title(article.getTitle())
                .writerId(originalArticle.getWriterId())
                .content(article.getContent())
                .createdDate(originalArticle.getCreatedDate())
                .commentsCount(originalArticle.getCommentsCount())
                .build());
        return true;
    }

    @Override
    public boolean deleteArticle(long articleId) {
        if (!STORED_ARTICLES.containsKey(articleId)) {
            return false;
        }

        STORED_ARTICLES.remove(articleId);
        return true;
    }
}
