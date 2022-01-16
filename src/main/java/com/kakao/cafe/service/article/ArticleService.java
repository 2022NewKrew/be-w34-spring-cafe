package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.MemoryArticleRepository;
import com.kakao.cafe.web.dto.article.ArticleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    public ArticleService() {
    }

    private final ArticleRepository articleRepository = new MemoryArticleRepository();

    public void postArticle(Article article) {
        articleRepository.create(article);
    }

    public List<ArticleResponseDto> readAll() {
        return articleRepository.readAll().stream().map(
                article -> ArticleResponseDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .date(article.getDate())
                        .build()
        ).collect(Collectors.toList());
    }

    public ArticleResponseDto findById(String id){
        Article foundArticle = articleRepository.read(Long.parseLong(id));
        return ArticleResponseDto.builder()
                .title(foundArticle.getTitle())
                .id(foundArticle.getId())
                .content(foundArticle.getContent())
                .date(foundArticle.getDate())
                .build();
    }
}
