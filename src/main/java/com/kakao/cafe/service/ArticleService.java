package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.ArticlesDTO;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());

        articleRepository.save(article);
    }

    public ArticlesDTO findAll() {
        List<Article> articles = articleRepository.findAll();

        if (articles.isEmpty()) {
            return null;
        }

        return new ArticlesDTO(articles);
    }

    public ArticleDTO findById(Integer articleIndex) {
        Article articleinfo = articleRepository.findByIndex(articleIndex);

        return new ArticleDTO(articleinfo);
    }

}
