package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.dto.ArticleListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void createArticle(Article article) {
        articleRepository.create(article);
    }

    public int getArticleListSize() {
        return articleRepository.getArticleList().size();
    }

    public List<ArticleDTO> getArticleList() {
        ArticleListDTO articleListDTO = new ArticleListDTO(articleRepository.getArticleList());
        return articleListDTO.getCopiedUserList();
    }

    public ArticleDTO getArticleByIndex(int id) {
        return new ArticleDTO(articleRepository.findById(id));
    }
}
