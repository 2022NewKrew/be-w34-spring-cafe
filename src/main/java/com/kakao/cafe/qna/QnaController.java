package com.kakao.cafe.qna;

import com.kakao.cafe.qna.dto.request.QnaRequest;
import com.kakao.cafe.qna.dto.response.QnaResponse;
import com.kakao.cafe.qna.dto.response.QnasResponse;
import com.kakao.cafe.user.UserService;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class QnaController {

    private final QnaService qnaService;
    private final UserService userService;

    public QnaController(QnaService qnaService, UserService userService) {
        this.qnaService = qnaService;
        this.userService = userService;
    }

    @PostMapping("/questions")
    public String createQna(QnaRequest qnaRequest, HttpSession httpSession) {
        UUID sessionId = (UUID) httpSession.getAttribute("sessionId");
        qnaRequest.setWriter(userService.findUserIdBySessionId(sessionId));
        qnaService.save(qnaRequest);
        return "redirect:/";
    }

    @GetMapping("questions/form")
    public String createForm(HttpSession httpSession, Model model) {
        UUID sessionId = (UUID) httpSession.getAttribute("sessionId");
        String userName = userService.findUserIdBySessionId(sessionId);
        model.addAttribute("userName", userName);
        return "qna/form";
    }

    @GetMapping("questions/form/{id}")
    public String updateForm(@PathVariable long id, HttpSession httpSession, Model model) {
        UUID sessionId = (UUID) httpSession.getAttribute("sessionId");
        String userId = userService.findUserIdBySessionId(sessionId);
        QnaResponse qnaResponse = qnaService.findById(id, userId);
        model.addAttribute("qna", qnaResponse);
        return "qna/updateForm";
    }

    @PutMapping("questions/{id}")
    public String update(@PathVariable long id, QnaRequest qnaRequest, HttpSession httpSession) {
        UUID sessionId = (UUID) httpSession.getAttribute("sessionId");
        qnaRequest.setWriter(userService.findUserIdBySessionId(sessionId));
        qnaService.update(id, qnaRequest);
        return "redirect:/questions/" + id;
    }

    @DeleteMapping("questions/{id}")
    public String delete(@PathVariable long id, HttpSession httpSession) {
        UUID sessionId = (UUID) httpSession.getAttribute("sessionId");
        String userId = userService.findUserIdBySessionId(sessionId);
        qnaService.delete(id, userId);
        return "redirect:/";
    }

    @GetMapping()
    public String index(Model model) {
        QnasResponse qnasResponse = qnaService.findAll();
        model.addAttribute("qnas", qnasResponse.getQnaResponses());
        return "index";
    }

    @GetMapping("questions/{id}")
    public String findQna(@PathVariable long id, Model model) {
        QnaResponse qnaResponse = qnaService.findById(id);
        model.addAttribute("qna", qnaResponse);
        return "qna/show";
    }
}
