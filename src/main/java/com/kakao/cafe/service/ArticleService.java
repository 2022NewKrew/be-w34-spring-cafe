package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ArticleService {
    void postOne(ArticlePostDto a) throws SQLException, NoSuchElementException;
    List<ArticleDto> getArticleList();
    ArticleDto findById(int id) throws NoSuchElementException;

}
