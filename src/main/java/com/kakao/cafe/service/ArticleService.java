package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.ArticleVo;
import com.kakao.cafe.service.validation.ArticleValidation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleDao articleDao;
    private final ArticleValidation articleValidation;

    public ArticleService(ArticleDao articleDao, ArticleValidation articleValidation) {
        this.articleDao = articleDao;
        this.articleValidation = articleValidation;
    }

    public List<ArticleDto> getArticleList() {
        return articleDao.findAllArticle().stream()
                .map(this::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public ArticleDto filterArticleByIndex(int index) {
        ArticleVo articleVo = articleDao.filterArticleByIndex(index);
        articleValidation.validateArticle(articleVo);
        return voToDtoMapper(articleVo);
    }

    public void writeArticle(ArticleDto article, UserDto user) {
        article.setWriter(user.getName());
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
        ArticleDto articleDto = new ArticleDto(articleVo.getTitle(), articleVo.getContents());
        articleDto.setId(articleVo.getId());
        articleDto.setWriter(articleVo.getWriter());
        return articleDto;
    }
}
