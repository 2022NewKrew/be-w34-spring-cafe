package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.CreateArticleDto;
import com.kakao.cafe.dto.ShowArticleDto;
import com.kakao.cafe.repository.ArticleDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDAOInterface articleDAO;

    @Autowired
    public ArticleService(ArticleDAOInterface articleDAO) {
        this.articleDAO = articleDAO;
    }

    public void save(CreateArticleDto createArticleDto) {
        Article article = new Article(createArticleDto);
        articleDAO.save(article);
    }

    public List<ShowArticleDto> findAll() {
        return articleDAO.findAll()
                .stream()
                .map(ShowArticleDto::new)
                .collect(Collectors.toList());
    }

    public ShowArticleDto findById(String id) {
        return new ShowArticleDto(articleDAO.findById(Long.parseLong(id)));
    }
}
