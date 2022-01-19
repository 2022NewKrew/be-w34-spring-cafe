package com.kakao.cafe.model.repository;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//@Repository
public class BoardRepositoryMemoryImpl implements BoardRepository {
    private static final Map<Long, Article> storedArticles = new LinkedHashMap<>();
    private static final Table<Long, Long, Comment> storedComments = HashBasedTable.create();

    private static long maxArticleId = 1L;

    @Override
    public boolean saveArticle(Article article) {
        article.setArticleId(maxArticleId++);
        article.setCreatedDate(LocalDateTime.now());
        storedArticles.put(article.getArticleId(), article);
        return true;
    }

    @Override
    public boolean saveComment(long articleId, Comment comment) {
        if (!storedArticles.containsKey(articleId)) {
            return false;
        }

        Article currArticle = storedArticles.get(articleId);
        long commentId = currArticle.getCommentsCount();

        comment.setArticleId(articleId);
        comment.setCommentId(commentId);
        comment.setCreatedDate(LocalDateTime.now());

        storedComments.put(articleId, commentId, comment);
        currArticle.setCommentsCount(++commentId);
        return true;
    }

    @Override
    public List<Article> findAllArticle() {
        return new ArrayList<>(storedArticles.values());
    }

    @Override
    public Optional<Article> findArticleByArticleId(long articleId) {
        return Optional.ofNullable(storedArticles.get(articleId));
    }

    @Override
    public List<Article> findArticlesByWriterId(String writerId) {
        return storedArticles.values().stream()
                .filter(comment -> comment.getWriterId().equals(writerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findCommentsByArticleId(long articleId) {
        return new ArrayList<>(storedComments.row(articleId).values());
    }

    @Override
    public Optional<Comment> findCommentByArticleIdAndCommentId(long articleId, long commentId) {
        return Optional.ofNullable(storedComments.get(articleId, commentId));
    }

    @Override
    public List<Comment> findCommentsByWriterId(String writerId) {
        return storedComments.values().stream()
                .filter(article -> article.getWriterId().equals(writerId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean modifyArticle(Article article) {
        long articleId = article.getArticleId();

        if (!storedArticles.containsKey(articleId)) {
            return false;
        }

        Article originalArticle = storedArticles.get(articleId);

        storedArticles.put(articleId, Article.builder()
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
    public boolean modifyComment(long articleId, Comment comment) {
        long commentId = comment.getCommentId();

        if (!storedComments.contains(articleId, commentId)) {
            return false;
        }

        Comment originalComment = storedComments.get(articleId, commentId);

        storedComments.put(articleId, commentId, Comment.builder()
                .articleId(articleId)
                .commentId(commentId)
                .writerId(originalComment.getWriterId())
                .content(originalComment.getContent())
                .createdDate(originalComment.getCreatedDate()).build());
        return true;
    }

    @Override
    public boolean deleteArticle(long articleId) {
        if (!storedArticles.containsKey(articleId)) {
            return false;
        }

        storedArticles.remove(articleId);
        return true;
    }

    @Override
    public boolean deleteCommentByArticleId(long articleId) {
        if (!storedComments.containsRow(articleId)) {
            return false;
        }

        Map<Long, Comment> rowMap = storedComments.row(articleId);
        int size = rowMap.size();

        for (long commentId : rowMap.keySet()) {
            storedComments.remove(articleId, commentId);
        }

        Article article = storedArticles.get(articleId);
        article.setCommentsCount(article.getCommentsCount() - size);
        return true;
    }

    @Override
    public boolean deleteComment(long articleId, long commentId) {
        if (!storedComments.contains(articleId, commentId)) {
            return false;
        }

        storedComments.remove(articleId, commentId);

        Article article = storedArticles.get(articleId);
        article.setCommentsCount(article.getCommentsCount() - 1);
        return true;
    }
}
