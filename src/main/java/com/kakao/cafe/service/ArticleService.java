package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.RequestArticleDto;
import com.kakao.cafe.dto.ResponseArticleDto;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.MemoryArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleService {
    @Autowired
    private ModelMapper modelMapper;

    private final ArticleRepository articleRepository = new MemoryArticleRepository();

    public void addPost(RequestArticleDto articleDto){
        Article article = modelMapper.map(articleDto, Article.class);
        articleRepository.save(article);
    }

    public List<ResponseArticleDto> findArticles(){
        return articleRepository.findAll().stream()
                .map(article -> modelMapper.map(article, ResponseArticleDto.class))
                .collect(Collectors.toList());
    }

    public ResponseArticleDto findOne(long id){
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 글이 존재하지 않습니다."));
        return modelMapper.map(article, ResponseArticleDto.class);
    }

}
