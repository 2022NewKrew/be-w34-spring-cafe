package com.kakao.cafe.controller;

import com.kakao.cafe.dto.answer.AnswerSaveDto;
import com.kakao.cafe.dto.user.SessionUser;
import com.kakao.cafe.exception.NotAuthorizedException;
import com.kakao.cafe.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/questions/{questionId}/answers")
    public String save(@RequestParam String contents, @PathVariable int questionId, HttpSession session) {
        SessionUser sessionUser = (SessionUser)session.getAttribute("sessionUser");
        answerService.save(AnswerSaveDto.builder()
                        .userId(sessionUser.getId())
                        .questionId(questionId)
                        .contents(contents)
                        .build());
        return "redirect:/questions/"+questionId;
    }

    @DeleteMapping("/questions/{questionId}/answers/{id}")
    public String deleteById(@PathVariable int id, @PathVariable int questionId, HttpSession session) {
        SessionUser sessionUser = (SessionUser)session.getAttribute("sessionUser");
        if(sessionUser.getId() != answerService.findById(id).getUserId()){
            throw new NotAuthorizedException("로그인된 사용자 정보와 삭제하려는 답변의 작성자 정보가 다릅니다.");
        }
        answerService.deleteById(id);
        return "redirect:/questions/"+questionId;
    }
}
