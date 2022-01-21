package com.kakao.cafe.controller;

import com.kakao.cafe.controller.error.ErrorMessageBox;
import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.core.exception.IsNotAuthorOfThisComment;
import com.kakao.cafe.domain.reply.ReplyService;
import com.kakao.cafe.domain.reply.dto.ReplyCreateForm;
import com.kakao.cafe.domain.reply.dto.ReplyUpdateForm;
import com.kakao.cafe.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles/{id}/reply")
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("")
    public String createReply(@RequestParam String content, @PathVariable("id") Long articleId, @SessionAttribute(name= SessionConst.LOGIN_COOKIE) User user) {
        ReplyCreateForm replyCreateForm = ReplyCreateForm.builder()
                .articleId(articleId)
                .replyerId(user.getId())
                .content(content)
                .build();

        replyService.save(replyCreateForm);

        return "redirect:/articles/" + articleId;
    }

    @GetMapping("/{replyId}")
    public String getUpdateForm(@PathVariable Long replyId, @SessionAttribute(name = SessionConst.LOGIN_COOKIE) User user, Model model) {
        ReplyUpdateForm updateForm = replyService.getUpdateForm(replyId, user.getId());
        model.addAttribute("reply", updateForm);
        return "/qna/replyUpdateForm";
    }

    @PutMapping("/{replyId}")
    public String updateReply(@PathVariable(value="id") Long articleId, @PathVariable Long replyId, @RequestParam(value="content") String updateContent, @SessionAttribute(name= SessionConst.LOGIN_COOKIE) User user, Model model) {
        try{
            replyService.update(replyId, updateContent, user.getId());
            return "redirect:/articles/" + articleId;
        } catch (IsNotAuthorOfThisComment e) {
            model.addAttribute("error", new ErrorMessageBox("권한이 없습니다."));
            return "/error";
        }
    }

    @DeleteMapping("/{replyId}")
    public String deleteReply(@PathVariable(value="id") Long articleId, @PathVariable Long replyId, @SessionAttribute(name= SessionConst.LOGIN_COOKIE) User user, Model model) {
        try{
            replyService.delete(replyId, user.getId(), articleId);
            return "redirect:/articles/" + articleId;
        } catch (IsNotAuthorOfThisComment e) {
            return ErrorMessageBox.handling("권한이 없습니다.", model);
        }
    }
}
