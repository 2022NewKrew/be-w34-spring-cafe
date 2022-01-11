package com.kakao.cafe.controller;

import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.service.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String makeQna(@ModelAttribute QnaDto.CreateQnaRequest createQnaRequest) {
        qnaService.createQna(createQnaRequest);
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
