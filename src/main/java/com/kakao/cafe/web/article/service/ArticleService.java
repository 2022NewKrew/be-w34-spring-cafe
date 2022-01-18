package com.kakao.cafe.web.article.service;

import com.kakao.cafe.web.article.domain.Article;
import com.kakao.cafe.web.article.dto.ArticleCreateDTO;
import com.kakao.cafe.web.article.dto.ArticleUpdateDTO;
import com.kakao.cafe.web.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository memoryArticleRepository;

    public ArticleService(ArticleRepository memoryArticleRepository) {
        this.memoryArticleRepository = memoryArticleRepository;
    }

    public Article writeArticle(ArticleCreateDTO articleCreateDTO) {
        return memoryArticleRepository.save(articleCreateDTO);
    }

    public List<Article> getArticleList() {
        return memoryArticleRepository.getArticleList();
    }

    public Object getArticleListNotDeleted() {
        return memoryArticleRepository.getArticleListNotDeleted();
    }

    public Article getArticleById(long id) {
        Optional<Article> article = memoryArticleRepository.getArticleById(id);
        return article.orElseThrow(NoSuchElementException::new);
    }

    public Article updateArticle(ArticleUpdateDTO articleUpdateDTO) {
        return memoryArticleRepository.update(articleUpdateDTO);
    }

    public void deleteArticleById(long id) {
        memoryArticleRepository.deleteArticleById(id);
    }


}
