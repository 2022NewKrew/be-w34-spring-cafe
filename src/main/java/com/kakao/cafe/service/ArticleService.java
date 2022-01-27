package com.kakao.cafe.service;


import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.repository.ArticleRepository;
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

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void addArticle(String author, SampleArticleForm form){
        logger.info("addArticle author : {}, title : {}", author, form.getTitle());
        Article article = Article.add(author, form);
        articleRepository.save(article);
    }

    public void updateArticle(Long articleID, SampleArticleForm form){
        logger.info("updateArticle articleID : {}, title : {}",articleID, form.getTitle());
        Article article = articleRepository.findByID(articleID);
        article.update(form);
        articleRepository.update(article);
    }

    public Article findArticle(Long articleID){
        return articleRepository.findByID(articleID);
    }

    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    public void deleteArticle(Long articleID){
        logger.info("deleteArticle articleID : {}", articleID);
        articleRepository.delete(articleID);
    }

}
