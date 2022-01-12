package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.vo.ArticleVo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDao articleDAO;

    public ArticleService(ArticleDao articleDAO) {
        this.articleDAO = articleDAO;
    }

    public List<ArticleDto> getArticleList() {
        return articleDAO.findAllArticle().stream()
                .map(this::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public ArticleDto filterArticleByIndex(int index) {
        return voToDtoMapper(articleDAO.filterArticleByIndex(index));
    }

    public void writeArticle(ArticleDto article) {
        articleDAO.writeArticle(dtoToVoMapper(article));
    }

    private ArticleVo dtoToVoMapper(ArticleDto articleDto) {
        return new ArticleVo(articleDto.getWriter(), articleDto.getTitle(), articleDto.getContents());
    }

    private ArticleDto voToDtoMapper(ArticleVo articleVo) {
        ArticleDto articleDto = new ArticleDto(articleVo.getWriter(), articleVo.getTitle(), articleVo.getContents());
        articleDto.setId(articleVo.getId());
        return articleDto;
    }
}
