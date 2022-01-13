package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QnaController {
    private final QnaService qnaService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public QnaController() {
        qnaService = new QnaService();
    }

    @PostMapping("/questions")
    public String createQna(Qna qna) {
        qnaService.createQna(qna);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getQnas(Model model) {
        final List<Qna> qnas = qnaService.getQnas();

        logger.info("조회할 Qnas : {}", qnas);

        model.addAttribute("qnas", qnas);
        model.addAttribute("qnasSize", qnas.size());
        return "index";
    }

    @GetMapping("/articles/{qnaId}")
    public String getQna(@PathVariable("qnaId") long qnaId, Model model) {
        final Qna qna = qnaService.getQna(qnaId);

        logger.info("조회할 Qna : {}", qna);

        model.addAttribute("qna", qna);
        return "post/show";
    }
}
