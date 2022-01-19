package com.kakao.cafe.repository.comment;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Repository
public class MemoryCommentRepository implements CommentRepository {

    private static Long sequence = 0L;
    private final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment edit() {
        return null;
    }

    @Override
    public Comment delete() {
        return null;
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> findAllOfArticle(Article article) {
        return store.values().stream().filter(a ->
                a.getArticle().getArticleId().equals(article.getArticleId())
        ).collect(Collectors.toList());
    }
}
