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

    @GetMapping("/questions/{index}/updateform")
    public String updateQnaForm(@PathVariable("index") Integer index, Model model, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        QnaDto.QnaForUpdateResponse qnaForUpdate = qnaService.findQnaForUpdate(index, sessionedUser.getUserId());

        model.addAttribute("qna", qnaForUpdate);
        return "qna/updateForm";
    }

    @PutMapping("/questions/{index}")
    public String updateQna(@PathVariable("index") Integer index, @ModelAttribute QnaDto.UpdateQnaRequest updateQnaRequest, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        qnaService.updateQna(index, updateQnaRequest, sessionedUser.getUserId());
        return "redirect:/";
    }

    @DeleteMapping("/questions/{index}")
    public String deleteQna(@PathVariable("index") Integer index, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        qnaService.deleteQna(index, sessionedUser.getUserId());
        return "redirect:/";
    }

    @GetMapping("/")
    public String findQnaList(Model model) {
        List<QnaDto.QnaResponse> qnaList = qnaService.findQnaList();
        model.addAttribute("qnaList", qnaList);
        return "qna/list";
    }

    @GetMapping("/questions/{index}")
    public String findQna(@PathVariable("index") Integer index, Model model) {
        QnaDto.QnaResponse qna = qnaService.findQna(index);
        model.addAttribute("qna", qna);
        return "qna/show";
    }

    @PostMapping("/questions/{index}/comments")
    public String makeComment(@PathVariable("index") Integer index, @ModelAttribute CommentDto.CreateCommentRequest createCommentRequest,
                              HttpSession session) {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.createComment(index, sessionedUser.getUserId(), createCommentRequest.getContents());

        return "redirect:/questions/" + index;
    }

    @GetMapping("/questions/{index}/comments/{id}/updateform")
    public String updateCommentForm(@PathVariable("index") Integer index, @PathVariable Integer id, Model model, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        CommentDto.ReadCommentForUpdateResponse comment = commentService.readCommentForUpdate(id, sessionedUser.getUserId());

        model.addAttribute("comment", comment);
        return "qna/commentUpdateForm";
    }

    @PutMapping("/questions/{index}/comments/{id}")
    public String updateComment(@PathVariable("index") Integer index, @PathVariable("id") Integer id, @ModelAttribute CommentDto.UpdateCommentRequest updateCommentRequest,
                                HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        commentService.updateComment(id, updateCommentRequest.getContents(), sessionedUser.getUserId());

        return "redirect:/questions/" + index;
    }
}
