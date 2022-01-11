package com.kakao.cafe.service;

import java.util.List;

import com.kakao.cafe.dto.request.ArticleCreateRequestDTO;
import com.kakao.cafe.dto.response.ArticleFindResponseDTO;

public interface ArticleService {
	void create(ArticleCreateRequestDTO articleCreateRequestDTO);

	List<ArticleFindResponseDTO> getAllArticle();

	ArticleFindResponseDTO getArticleById(int id);
}
