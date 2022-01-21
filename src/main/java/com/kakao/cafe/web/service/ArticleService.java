package com.kakao.cafe.web.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.Users;
import com.kakao.cafe.web.exception.AccessDeniedException;
import com.kakao.cafe.web.exception.UnauthorizedException;
import com.kakao.cafe.web.repository.ArticleRepository;
import com.kakao.cafe.web.repository.ReplyRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    private ArticleService(ArticleRepository articleRepository, ReplyRepository replyRepository) {
        this.articleRepository = articleRepository;
        this.replyRepository = replyRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.selectAllArticles();
    }

    public void addArticle(Article article, Users author) {
        article.setAuthor(author);
        articleRepository.insertArticle(article);
    }

    public Article getByArticleId(int id) {
        return articleRepository.selectByArticleId(id);
    }

    public void updateArticle(int id, int userId, Article updateArticle) {
        if (getByArticleId(id).getAuthor().getId() != userId)
            throw new UnauthorizedException();
        if (updateArticle.getTitle().isBlank())
            throw new IllegalArgumentException("제목이 빈 값일 수 없습니다.");
        if (updateArticle.getContent().isBlank())
            throw new IllegalArgumentException("내용이 빈 값일 수 없습니다.");
        articleRepository.updateArticle(id, updateArticle);
    }

    public void deleteArticle(int id, int userId) {
        if (getByArticleId(id).getAuthor().getId() != userId)
            throw new AccessDeniedException();
        List<Reply> replies = articleRepository.selectByArticleId(id).getReplies();
        replies.forEach(reply -> {
            if (reply.getAuthor().getId() != userId) throw new IllegalArgumentException("글을 삭제할 수 없습니다.");
        });
        replies.forEach(reply -> replyRepository.deleteReply(reply.getId()));
        articleRepository.deleteArticle(id);
    }

    public Article getArticleUpdateForm(int id, int userId) {
        Article article = getByArticleId(id);
        if (article.getAuthor().getId() != userId)
            throw new AccessDeniedException();
        return article;
    }
}
