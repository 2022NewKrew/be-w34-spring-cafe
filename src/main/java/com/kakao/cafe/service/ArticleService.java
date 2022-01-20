package com.kakao.cafe.service;

import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticlePostDto;
import com.kakao.cafe.dto.ReplyContentsDto;
import com.kakao.cafe.dto.ReplyDto;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ArticleService {
    void post(ArticlePostDto a) throws SQLException, NoSuchElementException;
    void update(int id, ArticlePostDto modifiedArticle) throws NoSuchElementException;
    void delete(int id) throws  NoSuchElementException;
    List<ArticleDto> getArticleList();
    ArticleDto findById(int id) throws NoSuchElementException;
}
