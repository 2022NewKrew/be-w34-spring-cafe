package com.kakao.cafe.controller;

import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final ArticleService articleService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("articles", articleService.findAll());
		return "index";
	}

}
