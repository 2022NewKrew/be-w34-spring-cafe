package com.kakao.cafe.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kakao.cafe.common.Constant;
import com.kakao.cafe.common.SessionUser;
import com.kakao.cafe.common.annotation.LoginCheck;
import com.kakao.cafe.common.exception.UserAuthException;
import com.kakao.cafe.user.dto.request.UserCreateRequestDTO;
import com.kakao.cafe.user.dto.request.UserLoginRequestDTO;
import com.kakao.cafe.user.dto.request.UserUpdateRequestDTO;
import com.kakao.cafe.user.dto.response.UserFindResponseDTO;
import com.kakao.cafe.user.dto.response.UserInfoResponseDTO;
import com.kakao.cafe.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

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

	@PostMapping("/create")
	public String create(UserCreateRequestDTO requestDto) {
		userService.create(requestDto);

		return "redirect:/users";
	}

	@LoginCheck
	@PostMapping("/{id}/update")
	public String updateUser(@SessionAttribute(Constant.SESSION_USER) SessionUser sessionUser, @PathVariable int id, UserUpdateRequestDTO requestDTO) {
		if (!StringUtils.equals(sessionUser.getUserId(), requestDTO.getUserId())) {
			throw new UserAuthException("권한이 없습니다.");
		}

		userService.update(id, requestDTO);

		return "redirect:/users";
	}

	@PostMapping("/login-check")
	public String login(HttpSession session, UserLoginRequestDTO requestDTO) {
		SessionUser sessionUser = userService.checkLogin(requestDTO);
		session.setAttribute(Constant.SESSION_USER, sessionUser);

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.SESSION_USER);

		return "redirect:/";
	}
}
