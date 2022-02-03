package com.kakao.cafe.service;


import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleReplyForm;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.List;

import static com.kakao.cafe.util.ErrorCode.NOT_EXIST_USER;

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

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    public Reply findReply(Long replyID){
        return replyRepository.findByReplyID(replyID);
    }

    public void deleteArticle(Long articleID){
        logger.info("deleteArticle articleID : {}", articleID);
        articleRepository.delete(articleID);
    }

    public void addReply(SampleReplyForm form, User user){
        Reply reply = Reply.add(form, user.getUid());
        replyRepository.save(reply);
    }

    public void deleteReply(Long replyID){
        logger.info("deleteArticle articleID : {}", replyID);
        replyRepository.delete(replyID);
        logger.info("delete success");
    }

    public List<Reply> getReplies(Long articleID){
        return replyRepository.findByArticleID(articleID);
    }

}
