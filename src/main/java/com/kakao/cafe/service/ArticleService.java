package com.kakao.cafe.service;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.web.dto.ArticleDTO;
import com.kakao.cafe.web.dto.ArticleResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponseDTO articleResponseDTOFromArticle(Article article){
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createUserId(article.getCreateUserId())
                .createDate(article.getCreateDate())
                .views(article.getViews())
                .build();
    }

    public void createArticle(ArticleDTO articleDTO) {
        articleRepository.create(
                Article.noneIdInstance(articleDTO)
        );
    }

    public int getArticleListSize() {
        return articleRepository.getArticleList().size();
    }

    public List<ArticleResponseDTO> getArticleList() {
        return articleRepository.getArticleList().stream().map(
                this::articleResponseDTOFromArticle).collect(Collectors.toList()
        );
    }

    public ArticleResponseDTO getArticleByIndex(int id) {
        return this.articleResponseDTOFromArticle(articleRepository.findById(id));
    }
}
