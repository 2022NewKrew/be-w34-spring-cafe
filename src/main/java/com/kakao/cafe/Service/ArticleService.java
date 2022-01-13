package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Repository.ArticleRepository;

import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public String post(Article article){
        articleRepository.saveArticle(article);
        return article.getTitle();
    }

    public List<Article> findArticles(){
        return articleRepository.findAllArticles();
    }

    public Optional<Article> findOne(String title){
        return articleRepository.findByTitle(title);
    }

    public Optional<Article> findOne(Long id){
        return articleRepository.findByArticleId(id);
    }

    public List<Comment> findComments(Long id) {
        return articleRepository.findCommentsOf(id);
    }

    public String postComment(Comment comment){
        articleRepository.saveComment(comment);
        return comment.getAuthor();
    }
}
