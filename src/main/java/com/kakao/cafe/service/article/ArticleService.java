package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.domain.Repository.article.ArticleRepository;
import com.kakao.cafe.dto.article.ArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public void postNewArticle(ArticleDto articleDto) {
        this.articleRepository.postNewArticle(articleDto.toEntity());
    }

    public List<ArticleDto> getArticleList() {
        List<ArticleDto> articleList = new ArrayList<>();
        for (Article article : this.articleRepository.findAllArticles()) {
            ArticleDto articleDto = new ArticleDto(article.getWriter(), article.getTitle(), article.getContents());
            articleList.add(articleDto);
        }
        return articleList;
    }

    public ArticleDto getArticleById(int articleId) {
        Article article = this.articleRepository.findArticleById(articleId);
        ArticleDto articleDto = new ArticleDto(article.getWriter(), article.getTitle(), article.getContents());
        return articleDto;
    }
}
