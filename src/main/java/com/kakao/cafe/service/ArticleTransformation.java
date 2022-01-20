package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.domain.Article;
import com.kakao.cafe.dto.ArticleDetailDto;
import com.kakao.cafe.dto.ArticleListDto;
import com.kakao.cafe.dto.ArticlePostDto;

public class ArticleTransformation {
    public ArticleListDto toArticleListDto(ArticleDao articleDao) {
        return new ArticleListDto(articleDao.getNumber(), articleDao.getId(), articleDao.getName(), articleDao.getTitle(), articleDao.getTimestamp());
    }

    public ArticleDetailDto toArticleDetailDto(ArticleDao articleDao) {
        return new ArticleDetailDto(articleDao.getNumber(), articleDao.getId(), articleDao.getName(), articleDao.getTitle(), articleDao.getContents(), articleDao.getTimestamp());
    }

    public Article toArticle(ArticlePostDto articlePostDto, String id, String name) {
        return new Article(id, name, articlePostDto.getTitle(), articlePostDto.getContents());
    }
}
