package com.kakao.cafe.adapter.in.presentation.user;

import com.kakao.cafe.application.user.dto.UpdateRequest;
import com.kakao.cafe.application.user.port.in.UpdateUserInfoUseCase;
import com.kakao.cafe.view.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserUpdateController {

    private static final Logger log = LoggerFactory.getLogger(UserUpdateController.class);

    private final UpdateUserInfoUseCase updateUserInfoUseCase;

    public UserUpdateController(UpdateUserInfoUseCase updateUserInfoUseCase) {
        this.updateUserInfoUseCase = updateUserInfoUseCase;
    }

    @PostMapping("/users/{userId}/form")
    public String update(@PathVariable String userId, UpdateRequest updateRequest, RedirectAttributes redirectAttributes) {
        try {
            updateUserInfoUseCase.updateUserInfo(userId, updateRequest);
            log.info("{} info updated", userId);
            return "redirect:/users";
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            String message = ErrorMessage.getErrorMessage(e);
            redirectAttributes.addAttribute("message", message);
            return "redirect:/errors";
        }
    }
}
