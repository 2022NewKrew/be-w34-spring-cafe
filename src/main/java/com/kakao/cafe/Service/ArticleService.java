package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Repository.ArticleRepository;
import com.kakao.cafe.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    public String post(Article article, Object sessionedUser) {
        validateNull(article);
        article.setAuthor(getAuthor(sessionedUser));
        articleRepository.saveArticle(article);
        return article.getTitle();
    }


    private void validateNull(Article article) {
        if (article.getTitle().isBlank() || article.getContent().isBlank()) {
            throw new IllegalArgumentException("제목이나 내용이 공백일 수는 없습니다.");
        }
    }

    private String getAuthor(Object sessionedUser) {
        User user = (User) sessionedUser;
        return user.getNickName();
    }

    public List<Article> findArticles() {
        return articleRepository.findAllArticles();
    }

    public Optional<Article> findOne(String title) {
        return articleRepository.findByTitle(title);
    }

    public Optional<Article> findOne(Long id) {
        return articleRepository.findByArticleId(id);
    }

    public List<Comment> findComments(Long id) {
        return commentRepository.findCommentsOf(id);
    }

    public void checkAuthorMatch(Long articleId, User sessionedUser) throws IllegalAccessException {
        Article originArticle = articleRepository.findByArticleId(articleId).get();
        if (!sessionedUser.getNickName().equals(originArticle.getAuthor())) {
            throw new IllegalAccessException("다른 사람의 글은 수정/삭제 할 수 없습니다.");
        }
    }

    public void edit(Long articleId, Article article) {
        validateNull(article);
        articleRepository.editArticle(articleId, article);
    }

    public void delete(Long articleId) {
        articleRepository.deleteArticle(articleId);
    }
}
