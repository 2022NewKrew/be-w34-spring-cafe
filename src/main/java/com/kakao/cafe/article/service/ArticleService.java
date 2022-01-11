package com.kakao.cafe.article.service;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.ArticlePostDto;
import com.kakao.cafe.article.model.ArticlePreviewDto;
import com.kakao.cafe.article.model.ArticleRequest;
import com.kakao.cafe.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void writeArticle(ArticleRequest articleRequest){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        articleRepository.save(Article.builder()
                .id((long) articleRepository.getNumberOfArticles() + 1)
                .author(articleRequest.getAuthor())
                .title(articleRequest.getTitle())
                .contents(articleRequest.getContents())
                .uploadTime(date.format(new Date()))
                .build());
    }

    public List<ArticlePreviewDto> getAllArticlePreview(){
        return articleRepository.findAll()
                .stream().map(ArticlePreviewDto::of)
                .collect(Collectors.toList());
    }

    public ArticlePostDto getArticleById(Long id){
        Article article = articleRepository.findOneById(id);
        return ArticlePostDto.of(article);
    }

}
