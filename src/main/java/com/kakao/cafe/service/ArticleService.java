package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleCreateDto;
import com.kakao.cafe.dto.ArticleShowDto;
import com.kakao.cafe.repository.ArticleJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleJdbcRepository articleRepository;

    @Autowired
    public ArticleService(ArticleJdbcRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(ArticleCreateDto articleCreateDto){
        articleRepository.save(articleCreateDto.toEntity());
    }

    public List<ArticleShowDto> findAll(){
        return articleRepository.findAll()
                .stream()
                .map(ArticleShowDto::new)
                .collect(Collectors.toList());
    }

    public ArticleShowDto findOne(Integer id){
        return new ArticleShowDto(articleRepository.findOne(id).orElseThrow());
    }
}
