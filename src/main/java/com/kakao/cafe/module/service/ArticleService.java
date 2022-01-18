package com.kakao.cafe.module.service;

import com.kakao.cafe.module.model.domain.Article;
import com.kakao.cafe.module.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;
import static com.kakao.cafe.module.model.dto.UserDtos.*;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    public void postArticle(ArticlePostDto articlePostDto, UserDto userDto) {
        modelMapper.map(userDto, articlePostDto);
        articleRepository.addArticle(modelMapper.map(articlePostDto, Article.class));
    }

    public ArticleReadDto showArticle(Long id) {
        return articleRepository.findArticleById(id);
    }

    public void updateArticle(Long id, ArticleUpdateDto articleUpdateDto) {
        articleRepository.updateArticle(id, articleUpdateDto.getTitle(), articleUpdateDto.getContents());
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteArticle(id);
    }
}
