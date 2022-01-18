package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class ArticleService implements Service<Article, ArticleDTO, Integer>{
    private final Repository<Article, ArticleDTO, Integer> articleRepository;

    @Autowired
    public ArticleService(Repository<Article, ArticleDTO, Integer> articleRepository){
        this.articleRepository = articleRepository;
    }

    @Override
    public Integer join(ArticleDTO articleDTO) {
        articleRepository.save(articleDTO);
        return articleDTO.getId();
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        articleRepository.delete(id);
    }

    @Override
    public void update(ArticleDTO articleDTO) {
        articleRepository.update(articleDTO);
    }

    public void updateArticle(ArticleDTO articleDTO) throws InputMismatchException {
        articleRepository.findById(articleDTO.getId())
                .orElseThrow(() -> new InputMismatchException(articleDTO.getId() + "로 등록된 게시글을 찾울 수 없습니다."));

        update(articleDTO);
    }
}
