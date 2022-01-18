package com.kakao.cafe.controller.user;

import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;

	@GetMapping("/users")
	public String findAll(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}

	@PostMapping("/users")
	public String save(@ModelAttribute() UserDto userDto) {
		userService.save(userDto);
		return "redirect:/users";
	}

	@GetMapping("/users/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "user/profile";
	}

	@GetMapping("/users/{id}/form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "user/updateForm";
	}

	@PutMapping("/users/{id}/update")
	public String update(@PathVariable int id, @ModelAttribute() UserDto userDto) {
		try {
			userService.update(id, userDto);
		} catch (IllegalArgumentException e) {
			return "redirect:/error";
		}
		return "redirect:/users";
	}
}
