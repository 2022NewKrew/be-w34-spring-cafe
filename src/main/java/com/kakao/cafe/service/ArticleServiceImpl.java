package com.kakao.cafe.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kakao.cafe.dto.request.ArticleCreateRequestDTO;
import com.kakao.cafe.dto.response.ArticleFindResponseDTO;
import com.kakao.cafe.entity.Article;
import com.kakao.cafe.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
	private final ArticleRepository articleRepository;

	@Override
	public void create(ArticleCreateRequestDTO articleCreateRequestDTO) {
		articleRepository.save(articleCreateRequestDTO.toEntity());
	}

	@Override
	public List<ArticleFindResponseDTO> getAllArticle() {
		List<ArticleFindResponseDTO> articleFindList = new ArrayList<>();

		for (Article article : articleRepository.findAll()) {
			articleFindList.add(
				ArticleFindResponseDTO.builder()
					.id(article.getId())
					.writer(article.getWriter())
					.title(article.getTitle())
					.contents(article.getContents())
					.createTime(article.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
					.build()
			);
		}

		return articleFindList;
	}

	@Override
	public ArticleFindResponseDTO getArticleById(int id) {
		Article article = articleRepository.findById(id);

		return ArticleFindResponseDTO.builder()
			.id(article.getId())
			.writer(article.getWriter())
			.title(article.getTitle())
			.contents(article.getContents())
			.createTime(article.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
			.build();
	}
}
