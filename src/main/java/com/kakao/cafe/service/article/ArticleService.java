package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

	private final ArticleRepository memoryArticleRepository;
	private final ModelMapper modelMapper;

	public void save(ArticleDto articleDto) {
		memoryArticleRepository.save(modelMapper.map(articleDto, Article.class));
	}

	public List<Article> findAll() {
		return memoryArticleRepository.findAll();
	}

	public ArticleDto findByIndex(int index) {
		return modelMapper.map(memoryArticleRepository.findByIndex(index), ArticleDto.class);
	}
}
