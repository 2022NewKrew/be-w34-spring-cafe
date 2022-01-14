package com.kakao.cafe.repository;

import com.kakao.cafe.dto.article.ArticleCreateDTO;
import com.kakao.cafe.dto.article.ArticleDTO;

import java.util.*;

public interface ArticleRepository {

    ArticleDTO save(ArticleCreateDTO articleCreateDTO);

    Optional<ArticleDTO> findById(Long id);

    List<ArticleDTO> findAll();

}