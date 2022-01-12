package com.kakao.cafe.service;

import com.kakao.cafe.entiry.Article;
import com.kakao.cafe.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article register(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> getAllArticle(){
        return articleRepository.findAll();
    }

    public Article getArticle(Long id){
        return articleRepository.findById(id).orElseThrow(() -> {throw new IllegalStateException("해당 게시글이 존재하지 않습니다.");});
    }
}
