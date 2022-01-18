package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.dto.UserDto;
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

    @Autowired
    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
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
        QnaDto.QnaForUpdateReponse qnaForUpdate = qnaService.findQnaForUpdate(index, sessionedUser.getUserId());

        model.addAttribute("qna", qnaForUpdate);
        return "qna/updateForm";
    }

    @PutMapping("/questions/{index}")
    public String updateQna(@PathVariable("index") Integer index, @ModelAttribute QnaDto.UpdateQnaRequest updateQnaRequest, HttpSession session) throws AccessDeniedException {
        UserDto.UserSessionDto sessionedUser = (UserDto.UserSessionDto) session.getAttribute("sessionedUser");
        updateQnaRequest.setWriter(sessionedUser.getUserId());
        qnaService.updateQna(index, updateQnaRequest);
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

}
