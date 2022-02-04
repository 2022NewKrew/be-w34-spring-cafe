package com.kakao.cafe.service;


import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleReplyForm;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ArticleService {

    private final static Logger logger = LoggerFactory.getLogger(ArticleService.class);
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;


    public ArticleService(ArticleRepository articleRepository, ReplyRepository replyRepository) {
        this.articleRepository = articleRepository;
        this.replyRepository = replyRepository;
    }

    public Article addArticle(String author, SampleArticleForm form){
        logger.info("addArticle author : {}, title : {}", author, form.getTitle());
        Article article = Article.add(author, form);
        articleRepository.save(article);
        return article;
    }

    public Article updateArticle(Long articleID, SampleArticleForm form){
        logger.info("updateArticle articleID : {}, title : {}",articleID, form.getTitle());
        Article article = articleRepository.findByID(articleID);
        article.update(form);
        articleRepository.update(article);
        return article;
    }

    public Article findArticle(Long articleID){
        return articleRepository.findByID(articleID);
    }

    public List<Article> findAllArticles(){
        return articleRepository.findAll();
    }

    public Reply findReply(Long replyID){
        return replyRepository.findByReplyID(replyID);
    }

    public void deleteArticle(Long articleID){
        logger.info("deleteArticle articleID : {}", articleID);
        articleRepository.delete(articleID);
    }

    public Reply addReply(SampleReplyForm form, User user){
        Reply reply = Reply.add(form, user.getUid());
        return replyRepository.save(reply);
    }

    public void deleteReply(Long replyID){
        logger.info("deleteArticle articleID : {}", replyID);
        replyRepository.delete(replyID);
    }

    public List<Reply> getReplies(Long articleID){
        return replyRepository.findByArticleID(articleID);
    }

}
