package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.vo.ArticleVo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<ArticleDto> getArticleList() {
        return articleDao.findAllArticle().stream()
                .map(this::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public ArticleDto filterArticleByIndex(int index) {
        return voToDtoMapper(articleDao.filterArticleByIndex(index));
    }

    public void writeArticle(ArticleDto article) {
        articleDao.writeArticle(dtoToVoMapper(article));
    }

    public void updateArticle(int index, ArticleDto article) {
        articleDao.updateArticle(index, dtoToVoMapper(article));
    }

    public void deleteArticle(int index) {
        articleDao.deleteArticle(index);
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
