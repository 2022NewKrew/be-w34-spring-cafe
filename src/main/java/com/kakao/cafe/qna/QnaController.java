package com.kakao.cafe.qna;

import com.kakao.cafe.qna.dto.request.QnaRequest;
import com.kakao.cafe.qna.dto.response.QnasResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QnaController {

    private final QnaService qnaService;

    public QnaController(QnaService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("/questions")
    public String createQna(QnaRequest qnaRequest) {
        qnaService.save(qnaRequest);
        return "redirect:/";
    }

    @GetMapping()
    public String index(Model model) {
        QnasResponse qnasResponse = qnaService.findAll();
        model.addAttribute("qnas", qnasResponse.getQnaResponses());
        return "index";
    }
}
