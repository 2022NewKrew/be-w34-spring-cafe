package com.kakao.cafe.controller;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ReplyRequest;
import com.kakao.cafe.mapper.ArticleMapper;
import com.kakao.cafe.mapper.ReplyMapper;
import com.kakao.cafe.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }
    @PostMapping("/questions/{id}/answers")
    public String createAnswer(@PathVariable Long id, ReplyRequest replyRequest, HttpSession session) {
        User replyUser = (User) session.getAttribute("sessionedUser");
        replyService.create(ReplyMapper.INSTANCE.toEntity(id,replyRequest,replyUser));
        return "redirect:/articles/" + id;
    }
}
