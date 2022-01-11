package com.kakao.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.dto.request.ArticleCreateRequestDTO;
import com.kakao.cafe.dto.response.ArticleFindResponseDTO;
import com.kakao.cafe.service.ArticleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	@PostMapping("/create")
	public String createQna(ArticleCreateRequestDTO requestDTO) {
		articleService.create(requestDTO);

		return "redirect:/";
	}

	@GetMapping("/{id}")
	public String getArticle(@PathVariable int id, Model model) {
		ArticleFindResponseDTO article = articleService.getArticleById(id);
		model.addAttribute("article", article);

		return "qna/show";
	}
}
