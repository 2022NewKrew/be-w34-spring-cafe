package com.kakao.cafe.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakao.cafe.dto.request.UserCreateRequestDTO;
import com.kakao.cafe.dto.request.UserUpdateRequestDTO;
import com.kakao.cafe.dto.response.UserFindResponseDTO;
import com.kakao.cafe.dto.response.UserInfoResponseDTO;
import com.kakao.cafe.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@PostMapping("/create")
	public String create(UserCreateRequestDTO requestDto) {
		userService.create(requestDto);

		return "redirect:/users";
	}

	@GetMapping
	public String getAllUser(Model model) {
		List<UserFindResponseDTO> users = userService.getAllUser();
		model.addAttribute("users", users);

		return "user/list";
	}

	@GetMapping("/{id}")
	public String getUser(@PathVariable int id, Model model) {
		UserFindResponseDTO user = userService.getUserById(id);
		model.addAttribute("user", user);

		return "user/profile";
	}

	@GetMapping("/{id}/form")
	public String getUserForm(@PathVariable int id, Model model) {
		UserInfoResponseDTO user = userService.getUserInfoById(id);
		model.addAttribute("user", user);

		return "user/updateForm";
	}

	@PostMapping("/{id}/update")
	public String updateUser(@PathVariable int id, UserUpdateRequestDTO requestDTO) {
		userService.update(id, requestDTO);

		return "redirect:/users";
	}
}
