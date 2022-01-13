package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.Entity.Article;
import com.kakao.cafe.domain.Repository.article.ArticleRepository;
import com.kakao.cafe.dto.article.ReferArticleDto;
import com.kakao.cafe.dto.article.WriteArticleDto;
import com.kakao.cafe.exceptions.NoSuchArticleException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

    public void postNewArticle(WriteArticleDto articleDto) {
        this.articleRepository.postNewArticle(articleDto.toEntity(articleRepository.getNextArticleId()));
    }

    public List<ReferArticleDto> getArticleList() {
        List<ReferArticleDto> articleList = this.articleRepository.findAllArticles().stream()
                .map(ReferArticleDto::new).collect(Collectors.toList());
        return articleList;
    }

    public ReferArticleDto getArticleById(int articleId) throws NoSuchArticleException {
        Article article = this.articleRepository.findArticleById(articleId);
        ReferArticleDto articleDto = new ReferArticleDto(article);
        return articleDto;
    }
}
