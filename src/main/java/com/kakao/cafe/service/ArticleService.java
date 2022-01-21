package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.exception.NoSuchArticleException;
import com.kakao.cafe.exception.UnauthenticatedArticleAccessException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    public ArticleService(ArticleRepository articleRepository, ReplyRepository replyRepository) {
        this.articleRepository = articleRepository;
        this.replyRepository = replyRepository;
    }

    public void registerArticle(Article article) {
        articleRepository.save(article);
    }

    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    public Article findArticleById(UUID articleId) {
        Article article = articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NoSuchArticleException("해당 게시글을 찾을 수 없습니다."));
        article.increaseViewCount();
        articleRepository.increaseViewCount(article);
        return article;
    }

    public Article getArticleByIdAndAuthor(UUID articleId, User user) {
        Article article = articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NoSuchArticleException("해당 게시글을 찾을 수 없습니다."));
        if (!article.isWriter(user)) {
            throw new UnauthenticatedArticleAccessException("다른 사람의 게시글을 수정, 삭제할 수 없습니다");
        }
        return article;
    }

    public void updateArticleByIdAndAuthor(Article article) {
        Article articleToUpdate = articleRepository.findArticleById(article.getArticleId())
                .orElseThrow(() -> new NoSuchArticleException("해당 게시글을 찾을 수 없습니다."));
        if (!articleToUpdate.isWriter(article.getWriter())) {
            throw new UnauthenticatedArticleAccessException("다른 사람의 게시글을 수정, 삭제할 수 없습니다");
        }
        articleRepository.update(article);
    }

    public void deleteArticleByIdAndAuthor(UUID articleId, User user) {
        Article article = articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NoSuchArticleException("해당 게시글을 찾을 수 없습니다."));
        if (!article.isWriter(user)) {
            throw new UnauthenticatedArticleAccessException("다른 사람의 게시글을 수정, 삭제할 수 없습니다");
        }
        List<Reply> replyList = replyRepository.findAllByArticleId(articleId);
        boolean isArticleDeletable = replyList.stream().
                allMatch((reply -> reply.isWriter(user)));
        if (!isArticleDeletable) {
            throw new UnauthenticatedArticleAccessException("다른 사람의 댓글이 존재할 경우 삭제할 수 없습니다.");
        }
        replyRepository.deleteByArticleId(articleId);
        articleRepository.delete(article);
    }
}
