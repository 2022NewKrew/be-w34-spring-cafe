package com.kakao.cafe.controller.user;

import com.kakao.cafe.aop.UserLoginCheck;
import com.kakao.cafe.aop.UserValidCheck;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

	@UserValidCheck
	@GetMapping("/users/{id}/form")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "user/updateForm";
	}

	@UserValidCheck
	@PutMapping("/users/{id}/update")
	public String update(@PathVariable int id, @ModelAttribute() UserDto userDto) {
		userService.update(id, userDto);
		return "redirect:/users";
	}

	@GetMapping("/users/login")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/users/login")
	public String login(@ModelAttribute() UserDto userDto, HttpSession session) {
		userService.login(userDto);
		session.setAttribute("sessionUser", userService.findByAccId(userDto.getAccId()));
		return "redirect:/";
	}

	@UserLoginCheck
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		session.setAttribute("sessionUser", null);
		return "redirect:/";
	}
}
