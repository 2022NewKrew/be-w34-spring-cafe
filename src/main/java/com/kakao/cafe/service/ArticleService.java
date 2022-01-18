package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.model.dto.ArticleDto;
import com.kakao.cafe.model.dto.UserDto;
import com.kakao.cafe.model.vo.ArticleVo;
import com.kakao.cafe.model.vo.UserVo;
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
                .map(ArticleService::voToDtoMapper)
                .collect(Collectors.toList());
    }

    public ArticleDto filterArticleByIndex(int index) {
        ArticleVo articleVo = articleDao.filterArticleByIndex(index);
        articleValidation.validateArticle(articleVo);
        return voToDtoMapper(articleVo);
    }

    public void writeArticle(ArticleDto article, UserDto user) {
        article.setWriter(UserService.dtoToVoMapper(user));
        articleDao.writeArticle(dtoToVoMapper(article));
    }

    public void updateArticle(int index, ArticleDto article) {
        articleDao.updateArticle(index, dtoToVoMapper(article));
    }

    public void deleteArticle(int index) {
        articleDao.deleteArticle(index);
    }

    public static ArticleVo dtoToVoMapper(ArticleDto articleDto) {
        return new ArticleVo(articleDto.getWriter(), articleDto.getTitle(), articleDto.getContents());
    }

    public static ArticleDto voToDtoMapper(ArticleVo articleVo) {
        ArticleDto articleDto = new ArticleDto(articleVo.getTitle(), articleVo.getContents());
        articleDto.setId(articleVo.getId());
        articleDto.setWriter(articleVo.getWriter());
        return articleDto;
    }
}
