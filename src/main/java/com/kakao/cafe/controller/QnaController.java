package com.kakao.cafe.controller;

import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
public class QnaController {

    private final QnaService qnaService;
    private final CommentService commentService;

    @Autowired
    public QnaController(QnaService qnaService, CommentService commentService) {
        this.qnaService = qnaService;
        this.commentService = commentService;
    }

    @GetMapping("/questions")
    public String makeQnaHtml() {
        return "qna/form";
    }

    @PostMapping("/questions")
    public String makeQna(@ModelAttribute QnaDto.CreateQnaRequest createQnaRequest, HttpSession session) {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        createQnaRequest.setWriter(sessionedUser.getUserId());
        qnaService.createQna(createQnaRequest);
        return "redirect:/";
    }

    @GetMapping("/questions/{qnaId}/updateform")
    public String updateQnaForm(@PathVariable("qnaId") Integer qnaId, Model model, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        QnaDto.QnaForUpdateResponse qnaForUpdate = qnaService.findQnaForUpdate(qnaId, sessionedUser.getUserId());

        model.addAttribute("qna", qnaForUpdate);
        return "qna/updateForm";
    }

    @PutMapping("/questions/{qnaId}")
    public String updateQna(@PathVariable("qnaId") Integer qnaId, @ModelAttribute QnaDto.UpdateQnaRequest updateQnaRequest, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        qnaService.updateQna(qnaId, updateQnaRequest, sessionedUser.getUserId());
        return "redirect:/";
    }

    @DeleteMapping("/questions/{qnaId}")
    public String deleteQna(@PathVariable("qnaId") Integer qnaId, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        qnaService.deleteQna(qnaId, sessionedUser.getUserId());
        return "redirect:/";
    }

    @GetMapping("/")
    public String findQnaList(Model model) {
        List<QnaDto.QnaResponse> qnaList = qnaService.findQnaList();
        model.addAttribute("qnaList", qnaList);
        return "qna/list";
    }

    @GetMapping("/questions/{qnaId}")
    public String findQna(@PathVariable("qnaId") Integer qnaId, Model model) {
        QnaDto.QnaResponse qna = qnaService.findQna(qnaId);
        model.addAttribute("qna", qna);
        return "qna/show";
    }

    @PostMapping("/questions/{qnaId}/comments")
    public String makeComment(@PathVariable("qnaId") Integer qnaId, @ModelAttribute CommentDto.CreateCommentRequest createCommentRequest,
                              HttpSession session) {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.createComment(qnaId, sessionedUser.getUserId(), createCommentRequest.getContents());

        return "redirect:/questions/" + qnaId;
    }

    @GetMapping("/questions/{qnaId}/comments/{commentId}/updateform")
    public String updateCommentForm(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId, Model model, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        CommentDto.ReadCommentForUpdateResponse comment = commentService.readCommentForUpdate(commentId, sessionedUser.getUserId());

        model.addAttribute("comment", comment);
        return "qna/commentUpdateForm";
    }

    @PutMapping("/questions/{qnaId}/comments/{commentId}")
    public String updateComment(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId, @ModelAttribute CommentDto.UpdateCommentRequest updateCommentRequest,
                                HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.updateComment(commentId, updateCommentRequest.getContents(), sessionedUser.getUserId());

        return "redirect:/questions/" + qnaId;
    }

    @DeleteMapping("/questions/{qnaId}/comments/{commentId}")
    public String deleteComment(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.deleteComment(commentId, sessionedUser.getUserId());

        return "redirect:/questions/" + qnaId;
    }
}
