package com.kakao.cafe.controller;

import com.kakao.cafe.dto.AddReplyDto;
import com.kakao.cafe.dto.UpdateReplyDto;
import com.kakao.cafe.exception.IncorrectUserException;
import com.kakao.cafe.exception.NotLoginException;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.service.SessionService;
import com.kakao.cafe.util.ErrorUtil;
import com.kakao.cafe.vo.Reply;
import com.kakao.cafe.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {

    private final ReplyService replyService;
    private final SessionService sessionService;

    public ReplyController(ReplyService replyService, SessionService sessionService) {
        this.replyService = replyService;
        this.sessionService = sessionService;
    }

    @PostMapping("/replys/create/{index}")
    public String createReply(@PathVariable int index, AddReplyDto addReplyDto, HttpSession session) throws NotLoginException {
        User loginUser = sessionService.getLoginUser(session);
        replyService.addReply(loginUser.getUserId(), addReplyDto.getContents(), index);
        return "redirect:/articles/" + index;
    }

    @GetMapping("/questions/answers/{id}/form")
    public String updateForm(@PathVariable int id, Model model, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        Reply reply = replyService.getReply(id);
        if(!ErrorUtil.checkSameString(loginUser.getUserId(), reply.getWriter()))
            throw new IncorrectUserException();
        model.addAttribute("reply", reply);
        return "/reply/updateForm";
    }

    @PutMapping("/articles/{articleId}/reply/{id}/edit")
    public String editReply(@PathVariable int articleId, @PathVariable int id,
                            UpdateReplyDto updateReplyDto, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);

        replyService.updateReply(updateReplyDto.getWriter(), updateReplyDto.getContents(), articleId, id, loginUser);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/questions/{articleId}/answers/{id}")
    public String deleteReply(@PathVariable int articleId, @PathVariable int id, HttpSession session) throws Exception {
        User loginUser = sessionService.getLoginUser(session);
        replyService.deleteReply(id, loginUser);
        return "redirect:/articles/" + articleId;
    }

}
