package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.domain.Repository.article.ArticleRepository;
import com.kakao.cafe.dto.article.ReferArticleDto;
import com.kakao.cafe.dto.article.WriteArticleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public void postNewArticle(WriteArticleDto articleDto) {
        this.articleRepository.postNewArticle(articleDto.toEntity(articleRepository.findAllArticles().size() + 1));
    }

    public List<ReferArticleDto> getArticleList() {
        List<ReferArticleDto> articleList = new ArrayList<>();
        for (Article article : this.articleRepository.findAllArticles()) {
            ReferArticleDto articleDto = new ReferArticleDto(article.getArticleId(), article.getWriter(), article.getTitle(), article.getContents());
            articleList.add(articleDto);
        }
        return articleList;
    }

    public ReferArticleDto getArticleById(int articleId) {
        Article article = this.articleRepository.findArticleById(articleId);
        ReferArticleDto articleDto = new ReferArticleDto(article.getArticleId(), article.getWriter(), article.getTitle(), article.getContents());
        return articleDto;
    }
}
