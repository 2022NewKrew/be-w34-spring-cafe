package com.kakao.cafe.Service;

import com.kakao.cafe.Domain.Article;
import com.kakao.cafe.Domain.Comment;
import com.kakao.cafe.Domain.User;
import com.kakao.cafe.Repository.ArticleRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final String UNKNOWN_AUTHOR = "익명";
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public String post(Article article, HttpSession session){
        validateNull(article);
        article.setAuthor(checkAuthor(session));
        articleRepository.saveArticle(article);
        return article.getTitle();
    }

    public String postComment(Comment comment, HttpSession session){
        validateNull(comment);
        comment.setAuthor(checkAuthor(session));
        articleRepository.saveComment(comment);
        return comment.getAuthor();
    }

    private void validateNull(Article article) {
        if(article.getTitle().isBlank() || article.getContent().isBlank()){
            throw new IllegalArgumentException("제목이나 내용이 공백일 수는 없습니다.");
        }
    }

    private void validateNull(Comment comment) {
        if(comment.getContent().isBlank()){
            throw new IllegalArgumentException("댓글의 내용이 공백일 수는 없습니다.");
        }
    }

    private String checkAuthor(HttpSession session) {
        Object value = session.getAttribute("sessionedUser");
        if (value != null) {
            User user = (User)value;
            return user.getNickName();
        }
        return UNKNOWN_AUTHOR;
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


}
