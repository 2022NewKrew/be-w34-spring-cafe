package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.service.QnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QnaController {
    private final QnaService qnaService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("/questions")
    public String createQna(Qna qna) {
        qnaService.save(qna);
        return "redirect:/";
    }

    @GetMapping("/")
    public String getQnas(Model model) {
        final List<Qna> qnas = qnaService.findAll();

        logger.info("조회할 Qnas : {}", qnas);

        model.addAttribute("qnas", qnas);
        return "index";
    }

    @GetMapping("/questions/{qnaId}")
    public String getQna(@PathVariable("qnaId") long qnaId, Model model, HttpSession session) {
        Object sessionValue = session.getAttribute("sessionedUser");

        if (sessionValue == null) {
            logger.info("로그인하지 않은 유저가 접근");
            return "redirect:/users/login";
        }

        final Qna qna = qnaService.findByQnaId(qnaId);
        logger.info("조회할 Qna : {}", qna);

        if (checkAuthor(sessionValue, qna)) {
            model.addAttribute("isAuthor", true);
        }

        model.addAttribute("qna", qna);

        return "post/show";
    }

    private boolean checkAuthor(Object sessionValue, Qna qna) {
        final User user = (User) sessionValue;
        return qna.getAuthor().equals(user.getNickname());
    }

    @GetMapping("/questions")
    public String showForm(HttpSession session) {
        Object sessionValue = session.getAttribute("sessionedUser");

        if (sessionValue == null) {
            logger.info("로그인하지 않은 유저가 접근");
            return "redirect:/users/login";
        }

        return "post/form";
    }

    @GetMapping("/questions/{qnaId}/modify")
    public String showModifyForm(@PathVariable("qnaId") long qnaId, Model model, HttpSession session) {
        final Qna qna = qnaService.findByQnaId(qnaId);

        Object sessionValue = session.getAttribute("sessionedUser");

        if (sessionValue == null) {
            logger.info("로그인하지 않은 유저가 접근");
            return "redirect:/users/login";
        }

        model.addAttribute("qna", qna);
        return "post/form";
    }

    @PutMapping("/questions")
    public String modifyQna(Qna qna) {
        logger.info("QNA 수정 : {}", qna.toString());
        qnaService.update(qna);

        return "redirect:/questions/" + qna.getQnaId();
    }

    @DeleteMapping("/questions")
    public String deleteQna(long qnaId) {
        logger.info("QNA 삭제 : {}", qnaId);
        qnaService.delete(qnaId);

        return "redirect:/";
    }
}
