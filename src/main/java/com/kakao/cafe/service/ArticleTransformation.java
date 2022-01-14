package com.kakao.cafe.service;

import com.kakao.cafe.dao.ArticleDao;
import com.kakao.cafe.dto.ArticleListDto;

public class ArticleTransformation {
    public ArticleListDto toArticleListDto(ArticleDao articleDao) {
        return new ArticleListDto(articleDao.getNumber(), articleDao.getWriter(), articleDao.getTitle(), articleDao.getTimestamp());
    }
}
