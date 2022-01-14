package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.article.ArticleCreationDTO;
import com.kakao.cafe.dto.article.ArticleDTO;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    public long post(ArticleCreationDTO dto) {
        Article newArticle = new Article(dto);
        articleRepository.save(newArticle);

        return newArticle.getId();
    }

    public List<ArticleDTO> findAllArticles() {
        return articleRepository.findAll().stream()
                .map(m -> modelMapper.map(m, ArticleDTO.class))
                .collect(Collectors.toList());
    }

    public ArticleDTO findById(long id) {
        return articleRepository.findById(id)
                .map(m -> modelMapper.map(m, ArticleDTO.class))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }
}
