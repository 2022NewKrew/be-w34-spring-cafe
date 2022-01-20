package com.kakao.cafe.service.article;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.repository.article.H2ArticleRepository;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

	private final H2ArticleRepository h2ArticleRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;

	public void save(ArticleDto articleDto) {
		Article article = modelMapper.map(articleDto, Article.class);

		LocalDate today = LocalDate.now();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		String formattedToday = today.format(dateFormatter);

		LocalTime now = LocalTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
		String formattedNow = now.format(timeFormatter);

		article.setCreatedDate(formattedToday);
		article.setCreatedTime(formattedNow);

		h2ArticleRepository.save(article);
	}

	public List<Article> findAll() {
		List<Article> articles = h2ArticleRepository.findAll();
		articles.forEach(this::mapWriter);
		return articles;
	}

	public ArticleDto findByIndex(int index) {
		return modelMapper.map(mapWriter(h2ArticleRepository.findByIndex(index)), ArticleDto.class);
	}

	public void update(int index, ArticleDto articleDto) {
		Article article = modelMapper.map(articleDto, Article.class);
		article.setIndex(index);
		h2ArticleRepository.update(article);
	}

	public void delete(int index) {
		h2ArticleRepository.delete(index);
	}

	private Article mapWriter(Article article) {
		article.setWriterObj(modelMapper.map(userService.findByName(article.getWriter()), User.class));
		return article;
	}
}
