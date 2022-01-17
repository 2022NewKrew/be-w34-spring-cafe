package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Comments;
import com.kakao.cafe.exception.ErrorMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

//@Repository
@RequiredArgsConstructor
@Slf4j
public class MemoryArticleRepository implements ArticleRepository {

    private final Map<Long, Article> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Article save(Article article) {
        Article newArticle = new Article(article.getTitle(), article.getText(), article.getAuthor(), article.getTime(), new Comments(new ArrayList<>()), ++sequence);
        store.put(newArticle.getArticleId(), newArticle);
        return newArticle;
    }

    @Override
    public Article findArticle(Long articleId) {
        if (!isArticleExist(articleId))
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_ARTICLE);
        return store.get(articleId);
    }

    @Override
    public List<Article> findAllArticle() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean isArticleExist(Long articleId) {
        return store.get(articleId) != null;
    }

    @Override
    public Article deleteArticle(Long articleId) {
        if (!isArticleExist(articleId))
            throw new NoSuchElementException(ErrorMessages.NO_SUCH_ARTICLE);
        return store.remove(articleId);
    }

    @Override
    public void deleteAllArticle() {
        store.clear();
    }
}
