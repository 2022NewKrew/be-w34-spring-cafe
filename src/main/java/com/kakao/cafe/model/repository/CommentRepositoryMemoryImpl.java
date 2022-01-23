package com.kakao.cafe.model.repository;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.kakao.cafe.model.domain.Article;
import com.kakao.cafe.model.domain.Comment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("CommentRepositoryMemoryImpl")
public class CommentRepositoryMemoryImpl implements CommentRepository {
    private static final Table<Long, Long, Comment> STORED_COMMENTS = HashBasedTable.create();
    private static final Map<Long, Article> STORED_ARTICLES = ArticleRepositoryMemoryImpl.STORED_ARTICLES;

    @Override
    public Optional<Comment> saveComment(long articleId, Comment comment) {
        if (!STORED_ARTICLES.containsKey(articleId)) {
            return Optional.empty();
        }

        Article currArticle = STORED_ARTICLES.get(articleId);
        long commentId = currArticle.getCommentsCount();

        comment.setArticleId(articleId);
        comment.setCommentId(commentId);
        comment.setCreatedDate(LocalDateTime.now());
        comment.setFormattedCreatedDate(comment.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        STORED_COMMENTS.put(articleId, commentId, comment);
        currArticle.setCommentsCount(++commentId);
        return Optional.of(comment);
    }

    @Override
    public List<Comment> findCommentsByArticleId(long articleId) {
        return new ArrayList<>(STORED_COMMENTS.row(articleId).values());
    }

    @Override
    public Optional<Comment> findCommentByArticleIdAndCommentId(long articleId, long commentId) {
        return Optional.ofNullable(STORED_COMMENTS.get(articleId, commentId));
    }

    @Override
    public List<Comment> findCommentsByWriterId(String writerId) {
        return STORED_COMMENTS.values().stream()
                .filter(comment -> comment.getWriterId().equals(writerId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean modifyComment(long articleId, Comment comment) {
        long commentId = comment.getCommentId();

        if (!STORED_COMMENTS.contains(articleId, commentId)) {
            return false;
        }

        Comment originalComment = STORED_COMMENTS.get(articleId, commentId);

        STORED_COMMENTS.put(articleId, commentId, Comment.builder()
                .articleId(articleId)
                .commentId(commentId)
                .writerId(originalComment.getWriterId())
                .content(originalComment.getContent())
                .createdDate(originalComment.getCreatedDate()).build());
        return true;
    }

    @Override
    public boolean deleteCommentByArticleId(long articleId) {
        if (!STORED_COMMENTS.containsRow(articleId)) {
            return false;
        }

        Map<Long, Comment> rowMap = STORED_COMMENTS.row(articleId);
        int size = rowMap.size();

        for (long commentId : rowMap.keySet()) {
            STORED_COMMENTS.remove(articleId, commentId);
        }

        Article article = STORED_ARTICLES.get(articleId);
        article.setCommentsCount(article.getCommentsCount() - size);
        return true;
    }

    @Override
    public boolean deleteComment(long articleId, long commentId) {
        if (!STORED_COMMENTS.contains(articleId, commentId)) {
            return false;
        }

        STORED_COMMENTS.remove(articleId, commentId);

        Article article = STORED_ARTICLES.get(articleId);
        article.setCommentsCount(article.getCommentsCount() - 1);
        return true;
    }
}
