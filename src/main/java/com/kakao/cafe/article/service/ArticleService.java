package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.dto.ArticleCreateDTO;
import com.kakao.cafe.article.repository.ArticleJdbcRepository;
import com.kakao.cafe.article.repository.ArticleMemoryRepository;
import com.kakao.cafe.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    //private ArticleRepository articleRepository = new ArticleMemoryRepository();
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public void articleCreate(ArticleCreateDTO articleCreateDTO){
        articleRepository.addArticle(articleCreateDTO);
    }

    public Article getArticle(Long sequence){
        if(sequence < 0 || sequence >= articleRepository.getArticles().size()){
            throw new RuntimeException("범위에 없는 sequence가 입력되었습니다.");
        }

        return articleRepository.getArticles().get(Math.toIntExact(sequence));
    }

    public List<Article> getAllArticles(){
        return articleRepository.getArticles();
    }
}
