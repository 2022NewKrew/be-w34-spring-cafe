package com.kakao.cafe.controller.article;

import com.kakao.cafe.aop.UserLoginCheck;
import com.kakao.cafe.aop.UserValidCheck;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class ArticleController {

	private final ArticleService articleService;
	private final HttpSession session;

	@UserLoginCheck
	@GetMapping("/articles/form")
	public String form() {
		return "qna/form";
	}

	@UserLoginCheck
	@PostMapping("/articles")
	public String save(@ModelAttribute() ArticleDto articleDto) {
		UserDto user = (UserDto) session.getAttribute("sessionUser");
		articleDto.setWriter(user.getName());
		articleService.save(articleDto);
		return "redirect:/";
	}

	@UserLoginCheck
	@GetMapping("/articles/{index}")
	public String findByIndex(@PathVariable int index, Model model) {
		model.addAttribute("article", articleService.findByIndex(index));
		return "qna/show";
	}

	@UserValidCheck
	@PutMapping("/articles/{index}")
	public String update(@PathVariable int index, @ModelAttribute ArticleDto articleDto) {
		UserDto user = (UserDto) session.getAttribute("sessionUser");
		articleDto.setWriter(user.getName());
		articleService.update(index, articleDto);
		return "redirect:/articles/" + index;
	}

	@UserValidCheck
	@GetMapping("/articles/{index}/update")
	public String updateForm(@PathVariable int index, Model model) {
		model.addAttribute("article", articleService.findByIndex(index));
		return "qna/update";
	}

	@UserValidCheck
	@DeleteMapping("/articles/{index}")
	public String delete(@PathVariable int index) {
		articleService.delete(index);
		return "redirect:/";
	}
}
