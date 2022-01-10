package com.kakao.cafe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.dto.request.UserCreateRequestDto;
import com.kakao.cafe.dto.response.UserFindResponseDto;
import com.kakao.cafe.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/create")
	public String create(UserCreateRequestDto requestDto) {
		userService.save(requestDto);

		return "redirect:/users";
	}

	@GetMapping
	public String getAllUser(Model model) {
		List<UserFindResponseDto> users = userService.getAllUser();
		model.addAttribute("users", users);

		return "user/list";
	}

	@GetMapping("/{id}")
	public String getUser(@PathVariable int id, Model model) {
		UserFindResponseDto user = userService.getUserById(id);
		model.addAttribute("user", user);

		System.out.println("asdasdasdsa");

		return "user/profile";
	}
}
