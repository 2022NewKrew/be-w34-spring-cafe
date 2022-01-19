package com.kakao.cafe.article.service;

import com.kakao.cafe.article.exception.ArticleNotFoundException;
import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.dto.ArticlePostDto;
import com.kakao.cafe.article.dto.ArticlePreviewDto;
import com.kakao.cafe.article.dto.ArticleRequest;
import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public void writeArticle(ArticleRequest articleRequest){
        Article article = modelMapper.map(articleRequest, Article.class);
        article.setId(articleRepository.getNumberOfArticles() + 1);
        article.setUploadTime(DateUtils.getCurrentDate());

        articleRepository.save(article);
    }

    public List<ArticlePreviewDto> getAllArticlePreview(){
        return articleRepository.findAll()
                .stream().map(article -> modelMapper.map(article, ArticlePreviewDto.class))
                .collect(Collectors.toList());
    }

    public ArticlePostDto getArticlePostDtoById(Long id){
        Article article = getArticleById(id);
        return modelMapper.map(article, ArticlePostDto.class);
    }

    public boolean isAuthor(Long id, String userId){
        Article article = getArticleById(id);
        return article.getAuthor().equals(userId);
    }

    public Article getArticleById(Long id){
        return articleRepository.findOneById(id).orElseThrow(ArticleNotFoundException::new);
    }
}
