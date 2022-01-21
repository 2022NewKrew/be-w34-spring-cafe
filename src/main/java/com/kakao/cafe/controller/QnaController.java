package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.LoginUser;
import com.kakao.cafe.dto.CommentDto;
import com.kakao.cafe.dto.PageInfo;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.service.CommentService;
import com.kakao.cafe.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

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
    public String makeQna(@ModelAttribute QnaDto.CreateQnaRequest createQnaRequest, @LoginUser UserDto.UserSessionDto loginUser) {
        createQnaRequest.setWriter(loginUser.getUserId());
        qnaService.createQna(createQnaRequest);
        return "redirect:/";
    }

    @GetMapping("/questions/{qnaId}/updateform")
    public String updateQnaForm(@PathVariable("qnaId") Integer qnaId, Model model, @LoginUser UserDto.UserSessionDto loginUser) throws AccessDeniedException {
        QnaDto.QnaForUpdateResponse qnaForUpdate = qnaService.findQnaForUpdate(qnaId, loginUser.getUserId());

        model.addAttribute("qna", qnaForUpdate);
        return "qna/updateForm";
    }

    @PutMapping("/questions/{qnaId}")
    public String updateQna(@PathVariable("qnaId") Integer qnaId, @ModelAttribute QnaDto.UpdateQnaRequest updateQnaRequest,
                            @LoginUser UserDto.UserSessionDto loginUser) throws AccessDeniedException {
        qnaService.updateQna(qnaId, updateQnaRequest, loginUser.getUserId());
        return "redirect:/";
    }

    @DeleteMapping("/questions/{qnaId}")
    public String deleteQna(@PathVariable("qnaId") Integer qnaId, @LoginUser UserDto.UserSessionDto loginUser) throws AccessDeniedException {
        qnaService.deleteQna(qnaId, loginUser.getUserId());
        return "redirect:/";
    }

    @GetMapping("/")
    public String findQnaList(Model model, @PageableDefault(size = 15, sort = "created_at", direction = Sort.Direction.DESC) Pageable page) {
        Page<QnaDto.QnaResponse> qnaList = qnaService.findQnaListWithPaging(page);
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("pageInfo", new PageInfo.QnaPageInfo(qnaList));
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
                              @LoginUser UserDto.UserSessionDto loginUser) {
        commentService.createComment(qnaId, loginUser.getUserId(), createCommentRequest.getContents());

        return "redirect:/questions/" + qnaId;
    }

    @GetMapping("/questions/{qnaId}/comments/{commentId}/updateform")
    public String updateCommentForm(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId, Model model,
                                    @LoginUser UserDto.UserSessionDto loginUser) throws AccessDeniedException {
        CommentDto.ReadCommentForUpdateResponse comment = commentService.readCommentForUpdate(commentId, loginUser.getUserId());

        model.addAttribute("comment", comment);
        return "qna/commentUpdateForm";
    }

    @PutMapping("/questions/{qnaId}/comments/{commentId}")
    public String updateComment(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId, @ModelAttribute CommentDto.UpdateCommentRequest updateCommentRequest,
                                @LoginUser UserDto.UserSessionDto loginUser) throws AccessDeniedException {
        commentService.updateComment(commentId, updateCommentRequest.getContents(), loginUser.getUserId());

        return "redirect:/questions/" + qnaId;
    }

    @DeleteMapping("/questions/{qnaId}/comments/{commentId}")
    public String deleteComment(@PathVariable("qnaId") Integer qnaId, @PathVariable("commentId") Integer commentId,
                                @LoginUser UserDto.UserSessionDto loginUser) throws AccessDeniedException {
        commentService.deleteComment(commentId, loginUser.getUserId());

        return "redirect:/questions/" + qnaId;
    }
}
